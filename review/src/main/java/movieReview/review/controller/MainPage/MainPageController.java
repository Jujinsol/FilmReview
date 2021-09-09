package movieReview.review.controller.MainPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainPageController {

    @GetMapping
    public String goMainPage(){
        return "MainPage/MainPage";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "로그아웃 되었습니다..";
    }
}
