package movieReview.review.controller.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.Session.SessionConst;
import movieReview.review.service.Join.Mail.MailService;
import movieReview.review.service.Join.joinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/Join")
public class JoinController {
    private final joinService joinService;
    private final MailService mailService;

    @Value("${spring.mail.username}")
    private String MailName;


    @GetMapping
    public String joinPage(
            @ModelAttribute("JoinForm") JoinForm joinForm
    ) {
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser(
            @Validated @ModelAttribute("JoinForm") JoinForm joinForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @SessionAttribute(name = SessionConst.JoinSession, required = false) String CompareResult
    ) {

        if (bindingResult.hasErrors()) {
            return "/Join/signUp";
        }

        if (CompareResult.equals("0")) {
            bindingResult.rejectValue("joinCode", "JoinForm.Email.NoEmailJoin");
            return "Join/signUp";
        }

        String join = joinService.join(joinForm);

        switch (join){
            case "JoinForm.id" :
                bindingResult.rejectValue("id", join);
                return "Join/signUp";
            case "fail" :
                bindingResult.rejectValue("fail", "JoinForm.fail");
                return "Join/signUp";
            case "관리자" :
                redirectAttributes.addAttribute("id", joinForm.getId());
                return "redirect:/Join/Man/{id}";
            case "사용자" :
                redirectAttributes.addAttribute("id",joinForm.getId());
                return "redirect:/Join/{id}";
        }
        return "Join/signUp";
    }


    @PostMapping("/emailCertification")
    @ResponseBody
    public boolean emailSign(@RequestParam String email,
                             HttpSession session) throws AddressException
    {
        if (mailService.checkEmail(email) == false) {
            return false;
        }

        int random = new Random().nextInt(1000000) + 1000; // 1000 ~ 99999 인증번호 난수생성
        String joinCode = String.valueOf(random);
        // 세션 저장 - 나중에 사용자가 작성한 인증번호와 같은지 확인하기 위한 용도
        session.setAttribute("joinCode", joinCode);
        // 전송
        return mailService.send(joinCode, MailName, mailService.checkCode(email), null);
    }


    @PostMapping("/compare")
    @ResponseBody
    public int compare(@RequestParam String joinCode,
                       @SessionAttribute(name = "joinCode") String serverCode,
                       HttpSession session
    ) {
        String result = mailService.checkCode(joinCode);
        if (result.equals("false")) {
            return 0;
        }
        // 1이면 인증성공, 0이면 실패
        int CompareResult = mailService.JoinCodeComparison(serverCode, result);
        session.setAttribute("code", Integer.toString(CompareResult));
        return CompareResult;
    }


    @GetMapping("/{id}")
    public String myInfo(@PathVariable("id") String id,
                         Model model,
                         HttpSession session) {
        session.invalidate();
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo", userInfo);
        return "/Join/signUpSuccess";
    }

    @GetMapping("/Man/{id}")
    public String MangerInfo(@PathVariable("id") String id,
                             Model model,
                             HttpSession session) {
        session.invalidate();
        mangerInfo mangerInfo = joinService.mangerInfo(id);
        model.addAttribute("mangerInfo", mangerInfo);
        return "/Join/MsignUpSuccess";
    }

}
