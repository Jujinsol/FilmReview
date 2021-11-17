package movieReview.review.controller.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.service.Join.Mail.MailService;
import movieReview.review.service.Join.joinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/Join")
public class JoinController {
    private final joinService joinService;
    private final MailService mailService;
    private ThreadLocal<Map<String, Object>> compareResult = new ThreadLocal<>();

    @Value("${spring.mail.username}")
    private String MailName;

    @GetMapping
    public String joinPage(@ModelAttribute("userInfo") userInfo userinfo, @ModelAttribute("mangerInfo") mangerInfo mangerinfo) {
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser(@Validated @ModelAttribute userInfo userinfo,
                           BindingResult userError,
                           @ModelAttribute mangerInfo mangerinfo,
                           BindingResult managerError,
                           RedirectAttributes redirectAttributes
    ) {
        if (managerError.hasErrors() || userError.hasErrors()) {
            log.info("errors={}", managerError);
            return "Join/signUp";
        }
        Map<String, Object> code = compareResult.get();
        if (code == null) {
            userError.rejectValue("joinCode", "NoEmailJoin");
            log.info("이메일 인증 미진행. code is Empty");
            return "Join/signUp";
        } else {
            int toJoinCode = (int) code.get("code");
            if (toJoinCode == 1) {
                //이메일 인증 성공. 회원가입진행
                // 이메일 인증 성공
                if (mangerinfo.getNumber() == null) {
                    // 사용자일경우
                    int join = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);

                    if (join == 1) {

                        userInfo joinResult = joinService.myInfo(userinfo.getId());
                        redirectAttributes.addAttribute("id", joinResult.getId());

                        return "redirect:/Join/{id}";
                    } else {
                        userError.rejectValue("id", "equal");
                        log.info("errors={}", userError);
                        return "Join/signUp";
                    }
                } else {
                    // 관리자일경우
                    int mangerJoin = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), mangerinfo.getNumber());

                    if (mangerJoin == 1) {
                        mangerInfo joinMangerResult = joinService.mangerInfo(userinfo.getId());
                        redirectAttributes.addAttribute("id", joinMangerResult.getId());


                        return "redirect:/Join/Man/{id}";
                    } else {
                        managerError.rejectValue("id", "equal");
                        log.info("errors={}", managerError);
                        return "Join/signUp";
                    }
                }
            } else {
                userError.rejectValue("joinCode", "EmailJoin");
                log.info("이메일 인증 실패");
                //이메일 인증 실패. 이메일인증 진행
                return "Join/signUp";
            }

        }
    }


    @PostMapping("/emailCertification")
    @ResponseBody
    public boolean emailSign(@RequestParam String email, HttpSession session) throws AddressException {
        if(mailService.checkEmail(email) == false){
            return false;
        }

        int random = new Random().nextInt(1000000) + 1000; // 1000 ~ 99999 인증번호 난수생성

        String joinCode = String.valueOf(random);

        // 세션 저장 - 나중에 사용자가 작성한 인증번호와 같은지 확인하기 위한 용도
        session.setAttribute("joinCode", joinCode);

        String subject = "회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");

        // 전송
        return mailService.send(subject, sb.toString(), MailName, email, null);
    }


    @PostMapping("/compare")
    @ResponseBody
    public int compare(@RequestParam String joinCode,
                       HttpSession session) {
        if(joinCode == null){
            return 0;
        }
        Map<String, Object> map = new HashMap<>();
        compareResult.set(map);
        Map<String, Object> comRes = compareResult.get();


        Object serverCode = session.getAttribute("joinCode");

        // 1이면 인증성공, 0이면 실패
        int CompareResult = mailService.JoinCodeComparison(serverCode.toString(), joinCode);
        comRes.put("code", CompareResult);
        return CompareResult;
    }


    @GetMapping("/{id}")
    public String myInfo(@PathVariable("id") String id, Model model) {
        compareResult.remove();
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo", userInfo);
        return "/Join/signUpSuccess";
    }

    @GetMapping("/Man/{id}")
    public String MangerInfo(@PathVariable("id") String id, Model model) {
        compareResult.remove();
        userInfo userInfo = joinService.myInfo(id);
        mangerInfo mangerInfo = joinService.mangerInfo(id);
        log.info("mangerInfo.getId()={}", mangerInfo.getId());

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("mangerInfo", mangerInfo);
        return "/Join/MsignUpSuccess";
    }

}
