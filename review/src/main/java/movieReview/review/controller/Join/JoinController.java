package movieReview.review.controller.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.Mail.MailServiceImpl;
import movieReview.review.service.Join.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/Join")
public class JoinController {
    private final joinServiceImpl joinService;
    private final MailServiceImpl mailService;


    @GetMapping
    public String joinPage(@ModelAttribute("userInfo") userInfo userinfo, @ModelAttribute("mangerInfo") mangerInfo mangerinfo) {
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser(@Validated @ModelAttribute userInfo userinfo, BindingResult userError, @ModelAttribute mangerInfo mangerinfo, BindingResult managerError, HttpSession session, RedirectAttributes redirectAttributes) throws MessagingException {
        if (managerError.hasErrors() || userError.hasErrors()) {
            log.info("errors={}", managerError);
            return "Join/signUp";
        }
        if (mangerinfo.getNumber() == null) {
            // 사용자일경우
            int join = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);

            if (join == 1) {
                Object joinCode = session.getAttribute("joinCode");

                userInfo joinResult = joinService.myInfo(userinfo.getId());
                redirectAttributes.addAttribute("id", joinResult.getId());
                redirectAttributes.addAttribute("password", joinResult.getPassword());
                redirectAttributes.addAttribute("email", joinResult.getEmail());

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
                redirectAttributes.addAttribute("password", joinMangerResult.getPassword());
                redirectAttributes.addAttribute("number", joinMangerResult.getNumber());
                redirectAttributes.addAttribute("email", joinMangerResult.getEmail());

                return "redirect:/Join/Man/{id}";
            } else {
                managerError.rejectValue("id", "equal");
                log.info("errors={}", managerError);
                return "Join/signUp";
            }


        }
    }

    @PostMapping("/emailCertification")
    @ResponseBody
    public boolean emailSign(userInfo userinfo,HttpSession session){
        String email = userinfo.getEmail();
        int random = new Random().nextInt(1000000)+1000; // 1000 ~ 99999

        String joinCode = String.valueOf(random);

        // 세션 저장
        session.setAttribute("joinCode", joinCode);

        String subject = "회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");
        return mailService.send(subject, sb.toString(), "jjs04122002@gmail.com", email, null);
    }


    @GetMapping("/{id}")
    public String myInfo(@PathVariable("id") String id, Model model) {
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo", userInfo);
        return "/Join/signUpSuccess";
    }

    @GetMapping("/Man/{id}")
    public String MangerInfo(@PathVariable("id") String id, Model model) {
        userInfo userInfo = joinService.myInfo(id);
        mangerInfo mangerInfo = joinService.mangerInfo(id);
        log.info("mangerInfo.getId()={}", mangerInfo.getId());

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("mangerInfo", mangerInfo);
        return "/Join/MsignUpSuccess";
    }

}
