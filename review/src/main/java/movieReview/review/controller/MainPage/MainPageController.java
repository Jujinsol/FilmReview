package movieReview.review.controller.MainPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.Session.SessionConst;
import movieReview.review.repository.MainPage.MainPageRepository;
import movieReview.review.service.MainPage.MainPageProxy.cacheProxy;
import movieReview.review.service.MainPage.MainPageService;
import movieReview.review.service.MainPage.MainPizzaShapeMake.GetAllPizzaShape;
import movieReview.review.service.RedisService.RedisService;
import movieReview.review.service.Upload.UploadService;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeService;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeServiceImpl;
import movieReview.review.service.reviewFunction.reviewUploadServie.ReviewUploadService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.rule.Mode;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService pageService;
    private final UploadService uploadService;
    private final GetAllPizzaShape getAllPizzaShape;

    @GetMapping
    // uri경로로 넘어오는 페이지번호를 받아서 출력
    public String goMainPage(Model model,
                             @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                             @SessionAttribute(required = false, value = SessionConst.LoginId) String id) throws ClassNotFoundException {
        List<String> pizzaShape = new ArrayList<>();

        Page<JpaMovieInfo> movieList = pageService.findAll(page); // 페이지 가져오기
        getAllPizzaShape.GetAllPizzaShape(movieList, pizzaShape); // 영화별 피자모형 생성

        model.addAttribute("SessionId",id); // 로그인한상태인지 확인하기 위해 세션값 모델에담아 전송
        model.addAttribute("nowPage", page); // 현재 페이지정보 전송
        model.addAttribute("boardList", movieList);
        model.addAttribute("totalPage", movieList.getTotalPages()); // 전체페이지 모델에담아 전송
        model.addAttribute("AllPizzaShape",pizzaShape);

        return "MainPage/MainPage";
    }

    @ResponseBody
    @GetMapping("/moviePhoto/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:"+uploadService.getFullPath(filename));
    }

}
