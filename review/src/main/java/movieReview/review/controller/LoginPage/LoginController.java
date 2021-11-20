package movieReview.review.controller.LoginPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.Login.loginForm;
import movieReview.review.Session.SessionConst;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.service.Login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/Login")
@Slf4j
public class LoginController {
    private final LoginService service;


    @GetMapping
    public String LoginPage(
            @ModelAttribute("loginForm") loginForm form)
    {
        return "LogIn/LogIn";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "로그아웃 되었습니다..";
    }

    @PostMapping
    public String doLogin(
            @Validated @ModelAttribute("loginForm") loginForm form,
            BindingResult bindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "LogIn/LogIn";
        }

        int result = service.loginResult(form);

        switch (result){
            case 1 :
                HttpSession session = request.getSession();
                session.setAttribute(SessionConst.LoginId, form.getId());
                if (redirectAttributes.getAttribute("redirectURL") != null) {
                    return "redirect:" + request.getParameter("redirectURL"); // 들어가는거 시도했던 페이지로 돌아감
                }
                return "redirect:/";
            case 0 :
                bindingResult.reject("GlobalLoginError.loginFail");
                return "LogIn/LogIn";
            case 2 :
                bindingResult.reject("GlobalLoginError.noSuchId");
                return "LogIn/LogIn";
        }

        return "LogIn/LogIn";
    }
}


