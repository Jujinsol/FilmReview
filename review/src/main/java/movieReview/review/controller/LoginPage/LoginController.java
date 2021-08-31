package movieReview.review.controller.LoginPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.Login.loginMangerInfo;
import movieReview.review.dto.Login.loginUserInfo;
import movieReview.review.service.Login.LoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/Login")
@Slf4j
public class LoginController {
    private final LoginServiceImpl loginServiceImpl;


    @GetMapping
    public String LoginPage(Model model) {
        model.addAttribute("loginUserInfo", new loginUserInfo());
        return "LogIn/LogIn";
    }

    @PostMapping
    public String doLogin(@Validated @ModelAttribute loginUserInfo userinfo, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors() ) {
            log.info("errors={}", bindingResult);
            return "LogIn/LogIn";
        }

        loginMangerInfo mangerinfo = new loginMangerInfo();

        int result = loginServiceImpl.FirstCheck(userinfo.getId());
        log.info("result={}",result);

        if (result == 1) {
            //사용자
            Optional<loginUserInfo> userLoginResult = Optional.ofNullable(loginServiceImpl.userLogin(userinfo));
            if (userLoginResult.isEmpty()) {
                bindingResult.reject("GlobalUserLoginWorn");
                log.info("user.errors={}", bindingResult);
                log.info("사용자 둘중에하나 틀렸음");
                return "LogIn/LogIn";
            } else {
                Cookie userCookie = new Cookie("id", String.valueOf(userinfo.getId()));
                response.addCookie(userCookie);
                return "MainPage/MainPage";
            }
        } else if (result == 2) {
            //관리자
            mangerinfo.setId(userinfo.getId());
            mangerinfo.setPassword(userinfo.getPassword());
            Optional<loginMangerInfo> mangerLoginResult = Optional.ofNullable(loginServiceImpl.mangerLogin(mangerinfo));

            if (mangerLoginResult.isEmpty()) {
                bindingResult.reject( "GlobalMangerLoginWorn");
                log.info("manger.errors={}", bindingResult);
                log.info("관리자 둘중에하나 틀렸음");
                return "LogIn/LogIn";
            } else {
                Cookie mangerCookie = new Cookie("id", String.valueOf(mangerinfo.getId()));
                response.addCookie(mangerCookie);
                return "MainPage/MainPage";
            }
        } else {
            //없는 회원 id
            bindingResult.reject("noSuchId");
            log.info("noSuchId.errors={}", bindingResult);
            return "LogIn/LogIn";
        }
    }
}


