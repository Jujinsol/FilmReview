package movieReview.review.controller.MainPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.MainPage.MainPageRepository;
import movieReview.review.service.MainPage.MainPageService;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeService;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeServiceImpl;
import movieReview.review.service.reviewFunction.reviewUploadServie.ReviewUploadService;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService pageService;
    private final ReviewUploadService reviewUploadService;
    private List<String> pizzaShape = new ArrayList<>(); // 모든영화 피자모형 갖고있음

    @GetMapping
    // uri경로로 넘어오는 페이지번호를 받아서 출력
    public String goMainPage(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) throws ClassNotFoundException {

        Page<JpaMovieInfo> movieList = pageService.findAll(page); // 페이지 가져오기
        GetAllPizzaShape(movieList, pizzaShape); // 영화별 피자모형 생성

        model.addAttribute("nowPage", page); // 현재 페이지정보 전송
        model.addAttribute("boardList", movieList);
        model.addAttribute("totalPage", movieList.getTotalPages()); // 전체페이지 모델에담아 전송
        model.addAttribute("AllPizzaShape",pizzaShape);
        return "MainPage/MainPage";
    }

    private void GetAllPizzaShape(Page<JpaMovieInfo> movieList,
                                  List<String> pizzaShape) throws ClassNotFoundException {
        int totalPoint = 0;
        for (JpaMovieInfo AllReview : movieList) {
            ReviewInfo reviewInfo = new ReviewInfo();
            reviewInfo.setPhotoOriName(AllReview.getPhotoOriName());
            List<JpaRevieTab> jpaRevieTabs = reviewUploadService.selectAllReview(reviewInfo);

            for (int i = 0; i < jpaRevieTabs.size(); i++) {
                totalPoint = 0;
                if (jpaRevieTabs.size() > 1) {
                    JpaRevieTab jpaRevieTab = jpaRevieTabs.get(i);
                    totalPoint += jpaRevieTab.getMoviePoint();
                } else {
                    JpaRevieTab jpaRevieTab = jpaRevieTabs.get(0);
                    totalPoint += jpaRevieTab.getMoviePoint();
                }
            }
            GradeService GradeInfo = new GradeServiceImpl(totalPoint);
            String pizza = GradeInfo.pizzaReturn(GradeInfo.average());
            pizzaShape.add(pizza); // 전체피자모형 리스트에 추가
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "로그아웃 되었습니다..";
    }

}
