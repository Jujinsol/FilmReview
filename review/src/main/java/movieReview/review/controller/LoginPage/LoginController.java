package movieReview.review.controller.LoginPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.dto.Login.loginMangerInfo;
import movieReview.review.dto.Login.loginUserInfo;
import movieReview.review.service.Login.LoginService;
import movieReview.review.service.Login.LoginServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/Login")
@Slf4j
public class LoginController {
    private final LoginService loginServiceImpl;


    @GetMapping
    public String LoginPage(Model model) {
        model.addAttribute("loginUserInfo", new loginUserInfo());
        return "LogIn/LogIn";
    }

    @PostMapping
    public String doLogin(@Validated @ModelAttribute loginUserInfo form,
                          BindingResult bindingResult,
                          HttpServletRequest request) {
        if (bindingResult.hasErrors() ) {
            log.info("errors={}", bindingResult);
            return "LogIn/LogIn";
        }

        loginMangerInfo mangerinfo = new loginMangerInfo();

        if (loginServiceImpl.FirstCheck(form.getId()) == 1) {
            //사용자
            Optional<loginUserInfo> userLoginResult = Optional.ofNullable(loginServiceImpl.userLogin(form));
            if (userLoginResult.isEmpty()) {
                bindingResult.reject("GlobalUserLoginWorn");
                log.info("user.errors={}", bindingResult);
                return "LogIn/LogIn";
            } else {
                HttpSession loginSession = request.getSession(true);
                loginSession.setAttribute(SessionConst.LoginId,form.getId());
                return "redirect:/MainPage";
            }

        } else if (loginServiceImpl.FirstCheck(form.getId()) == 2) {
            //관리자
            mangerinfo.setId(form.getId());
            mangerinfo.setPassword(form.getPassword());
            Optional<loginMangerInfo> mangerLoginResult = Optional.ofNullable(loginServiceImpl.mangerLogin(mangerinfo));

            if (mangerLoginResult.isEmpty()) {
                bindingResult.reject( "GlobalMangerLoginWorn");
                log.info("manger.errors={}", bindingResult);
                return "LogIn/LogIn";
            } else {
                HttpSession loginSession = request.getSession(true);
                loginSession.setAttribute(SessionConst.LoginId,form.getId());
                return "redirect:/MainPage";
            }
        } else {
            //없는 회원 id
            bindingResult.reject("noSuchId");
            log.info("noSuchId.errors={}", bindingResult);
            return "LogIn/LogIn";
        }
    }
}


