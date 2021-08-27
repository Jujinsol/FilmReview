package movieReview.review.controller.LoginPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Login.LoginServiceImpl;
import movieReview.review.validation.LoginValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/Login")
@Slf4j
public class LoginController {
    private final LoginServiceImpl loginServiceImpl;
    private final LoginValidation loginValidation;

//    @InitBinder
//    public void init(WebDataBinder dataBinder) {
//        dataBinder.addValidators(loginValidation);
//    }


    @GetMapping
    public String LoginPage(Model model) {
        model.addAttribute("userinfo", new userInfo());
        model.addAttribute("mangerinfo", new mangerInfo());
        return "/LogIn/LogIn";
    }

    @PostMapping
    public String doLogin( userInfo userinfo, mangerInfo mangerinfo, BindingResult bindingResult, HttpServletResponse response) {
        int result = loginServiceImpl.FirstCheck(userinfo.getId());

        loginValidation.validate(userinfo,bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "redirect:/Login";
        }

        if (result == 1) {
            //사용자
            Optional<userInfo> userLoginResult = Optional.ofNullable(loginServiceImpl.userLogin(userinfo));
            if (userLoginResult.isEmpty()) {
                bindingResult.rejectValue("id", "userLoginWorn");
                log.info("사용자 둘중에하나 틀렸음");
                return "redirect:/Login";
            } else {
                Cookie userCookie = new Cookie("id", String.valueOf(userinfo.getId()));
                response.addCookie(userCookie);
                return "/MainPage/MainPage";
            }
        } else if (result == 2) {
            //관리자
            mangerinfo.setId(userinfo.getId());
            mangerinfo.setPassword(userinfo.getPassword());
            mangerinfo.setEmail(userinfo.getEmail());
            Optional<mangerInfo> mangerLoginResult = Optional.ofNullable(loginServiceImpl.mangerLogin(mangerinfo));

            if (mangerLoginResult.isEmpty()) {
                bindingResult.rejectValue("id", "mangerLoginWorn");
                log.info("관리자 둘중에하나 틀렸음");
                return "redirect:/Login";
            } else {
                Cookie mangerCookie = new Cookie("id", String.valueOf(mangerinfo.getId()));
                response.addCookie(mangerCookie);
                return "/MainPage/MainPage";
            }
        } else {
            //없는 회원 id
            bindingResult.reject("noSuchId");
            return "redirect:/Login";
        }
    }
}


