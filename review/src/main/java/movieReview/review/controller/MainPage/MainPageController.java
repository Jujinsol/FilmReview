package movieReview.review.controller.MainPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.repository.MainPage.MainPageRepository;
import movieReview.review.service.MainPage.MainPageService;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainPageController{
    private final MainPageService pageService;

    @GetMapping
    // uri경로로 넘어오는 페이지번호를 받아서 출력
    public String goMainPage(Model model ,@RequestParam(required = false, defaultValue = "0", value = "page") int page){
        model.addAttribute("boardList",pageService.findAll(page));
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
