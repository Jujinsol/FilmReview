package movieReview.review.controller.MoviePageMain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import movieReview.review.service.GetMovieInfo.getMovieInfoService;
import movieReview.review.service.Upload.UploadService;
import movieReview.review.service.reviewFunction.reviewUploadServie.ReviewUploadService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/EachMovie")
@RequiredArgsConstructor
@Slf4j
// 각각의 영화 조회 ( 리뷰가능 )
public class EachMovie {
    private final UploadService uploadService;
    private final getMovieInfoService getMovieInfoService;
    private final photoUriInfo photoUriinfo;
    private final ReviewUploadService reviewUploadService; // 리뷰업로드기능

    @GetMapping("/getMovieInfo")
    public String EachMovieInfo(@RequestParam("photoOriName") String name, ReviewInfo reviewInfo, Model model){
        model.addAttribute("movieInfo",new movieInfo());
        reviewInfo.setPhotoOriName(name.substring(10));
        return "MoviePage/EachMovieInfo";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/viewOneMovie")
    @ResponseBody
    public List<JpaMovieInfo> MovieView(movieInfo movieinfo){
        List<JpaMovieInfo> jpaMovieInfos = new ArrayList<>();
        List<JpaMovieInfo> uri = new ArrayList<>();
        List<JpaMovieInfo> movie = getMovieInfoService.getMovie(movieinfo);
        for(int i = 0; i<movie.size(); i++){
            uri.add(makeUri(movie.get(i)));
            jpaMovieInfos.add(uri.get(i));
        }

        return jpaMovieInfos;
    }


    public JpaMovieInfo makeUri(JpaMovieInfo movie){
        photoUriinfo.setPhotoUri(movie.getPhotoUri());
        photoUriinfo.setPhotoOriName(movie.getPhotoOriName());

        photoUriInfo photoUriInfo = uploadService.showPhoto(photoUriinfo);
        ClassPathResource resource = new ClassPathResource("/moviePhoto/"+photoUriInfo.getPhotoOriName());

        Path path1 = Paths.get(resource.getPath());

        movie.setPhotoUri(path1.toString());
        return movie;
    }
}
