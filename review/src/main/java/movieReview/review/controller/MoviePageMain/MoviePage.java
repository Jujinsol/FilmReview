package movieReview.review.controller.MoviePageMain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.service.GetMovieInfo.getMovieInfoService;
import movieReview.review.service.GetMovieInfo.getMovieInfoServiceImpl;
import movieReview.review.service.Upload.UploadService;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import movieReview.review.service.GetMovieInfo.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oneMovie")
public class MoviePage {
    private final UploadService uploadService;
    private final getMovieInfoService getMovieInfoService;
    private final photoUriInfo photoUriinfo;

    @GetMapping
    public String goMovie(Model model){
        model.addAttribute("movieInfo",new movieInfo());
        return "MoviePage/MoviePageMain";
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

        log.info("path ={}",path1);
        movie.setPhotoUri(path1.toString());
        return movie;
    }

    @PostMapping("/reviewUpload")
    @ResponseBody
    public String reviewUpload(){
        // 로그인검증 필요함 추후 개발 예정 - 인터셉터로 개발 완료
        String result = "ok";
        return result;
    }
}
