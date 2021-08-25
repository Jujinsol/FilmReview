package movieReview.review.controller.LoginPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.checkMangerOrUser;
import movieReview.review.service.Join.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/Login")
@Slf4j
public class LoginController {
    private final joinServiceImpl joinService;
    private final checkMangerOrUser check;

    @GetMapping
    public String LoginPage(){
        return "/LogIn/LogIn";
    }

    @PostMapping
    public String doLogin(userInfo userinfo, BindingResult bindingResult){
        Optional<userInfo> FinduserInfo = Optional.ofNullable(joinService.myInfo(userinfo.getId()));
        Optional<mangerInfo> FindmangerInfo = Optional.ofNullable(joinService.mangerInfo(userinfo.getId()));
        if (FinduserInfo.isEmpty() && FindmangerInfo.isEmpty()){
            bindingResult.addError(new FieldError(
                    "userInfo",
                    "id",
                    userinfo.getId(),
                    false,
                    new String[]{"login.result.id"},
                    null,
                    null)
            );

        }


        return "/MainPage/MainPage";
    }
}
