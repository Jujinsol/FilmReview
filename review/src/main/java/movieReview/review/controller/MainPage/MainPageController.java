package movieReview.review.controller.MainPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
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
@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainPageController{
    private final MainPageService pageService;

    @GetMapping
    // uri경로로 넘어오는 페이지번호를 받아서 출력
    public String goMainPage(Model model ,@RequestParam(required = false, defaultValue = "0", value = "page") int page, @ModelAttribute("movieInfo") movieInfo movieInfo){
        Page<JpaMovieInfo> movieList = pageService.findAll(page); // 페이지 가져오기
        model.addAttribute("boardList",movieList);
        model.addAttribute("totalPage",movieList.getTotalPages()); // 전체페이지 모델에담아 전송
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
