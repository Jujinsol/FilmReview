package movieReview.review.controller.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.service.Join.Mail.MailServiceImpl;
import movieReview.review.service.Join.joinServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
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
    private final joinServiceImpl joinService;
    private final MailServiceImpl mailService;
    private Map<String,Object> compareResult = new HashMap<String,Object>();

    @Value("${spring.mail.username}")
    private String MailName;

    @GetMapping
    public String joinPage(@ModelAttribute("userInfo") userInfo userinfo, @ModelAttribute("mangerInfo") mangerInfo mangerinfo) {
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser(@Validated @ModelAttribute userInfo userinfo, BindingResult userError, @ModelAttribute mangerInfo mangerinfo, BindingResult managerError, RedirectAttributes redirectAttributes) throws MessagingException {
        if (managerError.hasErrors() || userError.hasErrors()) {
            log.info("errors={}", managerError);
            return "Join/signUp";
        }
        Optional<Object> code = Optional.ofNullable(compareResult.get("code"));
        if(code.isEmpty()){
            userError.rejectValue("joinCode","NoEmailJoin");
            log.info("이메일 인증 미진행. code is Empty");
            return "Join/signUp";
        }else{
            int toJoinCode = (int) code.get();
            if(toJoinCode==1){
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
            }else{
                userError.rejectValue("joinCode","EmailJoin");
                log.info("이메일 인증 실패");
                //이메일 인증 실패. 이메일인증 진행
                return "Join/signUp";
            }

        }
    }


    @PostMapping("/compare")
    @ResponseBody
    public Map<String, Object> compare(userInfo userinfo, HttpSession session) {
        Object joinCode = session.getAttribute("joinCode");

        // 1이면 인증성공, 0이면 실패
        int CompareResult = mailService.JoinCodeComparison(joinCode.toString(), userinfo.getJoinCode());
        compareResult.put("code",CompareResult);
        return compareResult;
    }

    @PostMapping("/emailCertification")
    @ResponseBody
    public Map<String,Object> emailSign(userInfo userinfo, HttpSession session) {
        Map<String,Object> result = new HashMap<String,Object>();

        int random = new Random().nextInt(1000000) + 1000; // 1000 ~ 99999 인증번호 난수생성

        String joinCode = String.valueOf(random);

        // 세션 저장 - 나중에 사용자가 작성한 인증번호와 같은지 확인하기 위한 용도
        session.setAttribute("joinCode", joinCode);

        String subject = "회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");

        // 전송
        boolean send = mailService.send(subject, sb.toString(),MailName , userinfo.getEmail(), null);
        result.put("sendResult",send);
        return result;
    }


    @GetMapping("/{id}")
    public String myInfo(@PathVariable("id") String id, Model model) {
        compareResult.clear();

        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo", userInfo);
        return "/Join/signUpSuccess";
    }

    @GetMapping("/Man/{id}")
    public String MangerInfo(@PathVariable("id") String id, Model model) {
        compareResult.clear();

        userInfo userInfo = joinService.myInfo(id);
        mangerInfo mangerInfo = joinService.mangerInfo(id);
        log.info("mangerInfo.getId()={}", mangerInfo.getId());

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("mangerInfo", mangerInfo);
        return "/Join/MsignUpSuccess";
    }

}
