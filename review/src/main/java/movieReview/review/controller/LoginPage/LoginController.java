package movieReview.review.controller.LoginPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.checkMangerOrUser;
import movieReview.review.service.Join.joinServiceImpl;
import movieReview.review.service.Login.Check.CheckInfoExistImpl;
import movieReview.review.service.Login.LoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String LoginPage(Model model){
        model.addAttribute("userinfo",new userInfo());
        model.addAttribute("mangerinfo",new mangerInfo());
        return "/LogIn/LogIn";
    }

    @PostMapping
    public String doLogin(userInfo userinfo, mangerInfo mangerinfo, BindingResult bindingResult, HttpServletResponse response){
        //아예 존재하지않는지
        //사용자인지
        //관리자인지 구분해야됌
        if(userinfo.getPassword()==null){
            bindingResult.addError(new FieldError("userinfo","password",userinfo.getId(),false,null,null,"잘못입력하셨습니다."));
            return "redirect:/Login";
        }else if(userinfo.getId()==null){
            bindingResult.addError(new FieldError("userinfo","ID",userinfo.getId(),false,null,null,"잘못입력하셨습니다."));
            return "redirect:/Login";
        }else if(userinfo.getPassword()==null&&userinfo.getId()==null){
            bindingResult.addError(new FieldError("userinfo","ID",userinfo.getId(),false,null,null,"잘못입력하셨습니다."));
            return "redirect:/Login";
        }else{
            int result = loginServiceImpl.FirstCheck(userinfo.getId());
            if(result==1){
                //사용자
                Optional<userInfo> userLoginResult = Optional.ofNullable(loginServiceImpl.userLogin(userinfo));
                if(userLoginResult.isEmpty()){
                    bindingResult.addError(new FieldError("userinfo","id",userinfo.getId(),false,null,null,"잘못입력하셨습니다."));
                    return "redirect:/Login";
                }else{
                    Cookie userCookie = new Cookie("id",String.valueOf(userinfo.getId()));
                    response.addCookie(userCookie);
                    return "/MainPage/MainPage";
                }
            }else if(result==2){
                //관리자
                mangerinfo.setId(userinfo.getId());
                mangerinfo.setPassword(userinfo.getPassword());
                mangerinfo.setEmail(userinfo.getEmail());
                Optional<mangerInfo> mangerLoginResult = Optional.ofNullable(loginServiceImpl.mangerLogin(mangerinfo));
                if(mangerLoginResult.isEmpty()){
                    bindingResult.addError(new FieldError("userinfo","id",userinfo.getId(),false,null,null,"잘못입력하셨습니다."));
                    return "redirect:/Login";
                }else{
                    Cookie mangerCookie = new Cookie("id",String.valueOf(mangerinfo.getId()));
                    response.addCookie(mangerCookie);
                    return "/MainPage/MainPage";
                }
            }else{
                //없는 회원 id
                bindingResult.addError(new FieldError("userinfo","id",userinfo.getId(),false,null,null,"없는 회원입니다.회원가입을 진행해 주세요."));
                return "redirect:/Login";
            }
        }
    }
}
