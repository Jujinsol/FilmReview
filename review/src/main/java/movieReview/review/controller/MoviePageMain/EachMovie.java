package movieReview.review.controller.MoviePageMain;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.service.GetMovieInfo.getMovieInfoService;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeService;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeServiceImpl;
import movieReview.review.service.reviewFunction.UserDuplicateCheck.UserDuplicateCheckService;
import movieReview.review.service.reviewFunction.reviewUploadServie.ReviewUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/EachMovie")
@RequiredArgsConstructor
@Slf4j
public class EachMovie {
    private final getMovieInfoService getMovieInfoService; // 영화정보가져오기
    private final ReviewUploadService reviewUploadService; // 리뷰업로드기능
    private final UserDuplicateCheckService DuplicateCheck; // 리뷰를 이미 달았는지 확인하는 서비스

    @GetMapping("/getMovieInfo")
    public String EachMovieInfo(
            Model model,
            @RequestParam("photoOriName") String photoOriName,
            @ModelAttribute("movieInfo") movieInfo movieinfo,
            @ModelAttribute("ReviewInfo") ReviewInfo reviewInfo,
            @SessionAttribute(name = SessionConst.LoginId, required = false) String id,
            HttpServletResponse response) throws ClassNotFoundException, UnsupportedEncodingException
    {
        if (photoOriName.contains("movie")) { // 검색기능통해 들어왔을경우
            response.addCookie(
                    new Cookie(
                            "photoOriName",
                            getMovieInfoService.getCookie(photoOriName, movieinfo, reviewInfo, model)
                    )
            ); // reviewUplaod에서 사용하기 위해 쿠키에 원본사진이름 저장 .
        } else { // 메인페이지를통해 들어왔을경우
            model.addAttribute("photoPath", photoOriName); // 사진경로 처리후 모델에 담아서 전송

            String cooke = URLEncoder.encode(photoOriName, "utf-8"); // 한글파일 쿠키저장위해 인코딩
            response.addCookie(new Cookie("photoOriName", cooke)); // reviewUplaod에서 사용하기 위해 쿠키에 원본사진이름 저장 .
        }
        List<JpaRevieTab> AllReviewsInfo = reviewUploadService.selectAllReview(reviewInfo);

        GradeService GradeInfo = new GradeServiceImpl(AllReviewsInfo);
        String PizzaPhoster = GradeInfo.pizzaReturn(GradeInfo.average());
        model.addAttribute("pizza",PizzaPhoster); // 피자모형 평점에맞게 만들어서 반환

        model.addAttribute("sessionId", id);
        model.addAttribute("oneMovieInfo", getMovieInfoService.EachMovie(movieinfo)); // photoOriName으로 영화정보 모델에담아서 전송
        model.addAttribute("AllReviewInfo", AllReviewsInfo); // 모든리뷰정보 가져와 모델에담아서 전송

        return "MoviePage/EachMovie";
    }


    @PostMapping("/reviewUpload")
    @ResponseBody
    public int reviewUpload(@SessionAttribute(value = SessionConst.LoginId, required = false) String id,
                            @CookieValue(name = "photoOriName", required = false) String photoOriName,
                            @Validated ReviewInfo reviewInfo,
                            BindingResult reviewError,
                            @RequestParam(value = "ids") String reviewIds) throws JsonProcessingException {
        if (id == null) {
            reviewError.rejectValue("reviewUser", "noUser", "로그인 안함");
        }

        if(DuplicateCheck.DuplicateCheck(id, reviewIds)){
            return 2;
        }

        if (reviewError.hasErrors()) {
            log.info("errors ={}", reviewError);
            return 0;
        }

        reviewInfo.setReviewUser(id);
        reviewInfo.setPhotoOriName(photoOriName);
        return reviewUploadService.reviewUpload(reviewInfo);
    }

    @DeleteMapping("/reviewDelete")
    @ResponseBody
    public int reviewDelete(@SessionAttribute(value = SessionConst.LoginId, required = false) String id,
                            ReviewInfo reviewInfo,
                            BindingResult reviewError){
        if (id == null) {
            reviewError.rejectValue("reviewUser", "noUser", "로그인 안함");
        }

        if (reviewError.hasErrors()) {
            return 0;
        }

        reviewInfo.setReviewUser(id);
        return reviewUploadService.deleteReview(reviewInfo);
    }
}
