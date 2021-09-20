package movieReview.review.controller.MoviePageMain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.JpaRevieTab;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import movieReview.review.service.GetMovieInfo.getMovieInfoService;
import movieReview.review.service.Upload.UploadService;
import movieReview.review.service.reviewFunction.reviewUploadServie.ReviewUploadService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/EachMovie")
@RequiredArgsConstructor
@Slf4j
public class EachMovie {
    private final getMovieInfoService getMovieInfoService; // 영화정보가져오기
    private final ReviewUploadService reviewUploadService; // 리뷰업로드기능

    @GetMapping("/getMovieInfo")
    public String EachMovieInfo(@RequestParam("photoOriName") String photoOriName,
                                @ModelAttribute("movieInfo") movieInfo movieinfo, // 검색기능을 위한 movieInfo
                                @ModelAttribute("ReviewInfo") ReviewInfo reviewInfo,// 에러처리용 ReviewInfo
                                Model model,
                                HttpServletResponse response) throws ClassNotFoundException {


        String originPhotoUri = photoOriName.substring(10); // 원본사진이름 추출
        movieinfo.setPhotoOriName(originPhotoUri); // 영화정보 가져오기위해서 set
        reviewInfo.setPhotoOriName(originPhotoUri); // 영화별 리뷰 전부다 가져오기위해서 set

        movieInfo oneMovieInfo = getMovieInfoService.EachMovie(movieinfo); // photoOriName으로 영화정보 하나 갖고옴

        Cookie idCookie = new Cookie("photoOriName",originPhotoUri);
        response.addCookie(idCookie); // reviewUplaod에서 사용하기 위해 쿠키에 원본사진이름 저장 .

        model.addAttribute("oneMovieInfo",oneMovieInfo); // 영화정보 모델에담아서 전송

//        ClassPathResource resource = new ClassPathResource("/moviePhoto/"+originPhotoUri);
//        Path photoPath = Paths.get(resource.getPath());
        model.addAttribute("photoPath",originPhotoUri); // 사진경로 처리후 모델에 담아서 전송

        List<JpaRevieTab> jpaRevieTabs = reviewUploadService.selectAllReview(reviewInfo);
        model.addAttribute("AllReviewInfo",jpaRevieTabs); // 모든리뷰정보 가져와 모델에담아서 전송

        return "MoviePage/EachMovie";
    }

    @GetMapping("/reviewUpload")
    @ResponseBody
    public int reviewUpload(@SessionAttribute(value = SessionConst.LoginId, required = false) String id,
                            @CookieValue(name="photoOriName",required = false) String photoOriName,
                            @Validated ReviewInfo reviewInfo, BindingResult reviewError){

        if(id==null){
            reviewError.rejectValue("reviewUser","noUser","로그인부터 진행해 주세요..");
        }

        if(reviewError.hasErrors()){
            log.info("errors ={}",reviewError);
            return 0;
        }

        reviewInfo.setReviewUser(id);
        reviewInfo.setPhotoOriName(photoOriName);
        return reviewUploadService.reviewUpload(reviewInfo);
    }
}
