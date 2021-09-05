package movieReview.review.controller.MoviePageMain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.service.GetMovieInfo.getMovieInfoServiceImpl;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oneMovie")
public class MoviePage {
    private final UploadServiceImpl uploadService;
    private final getMovieInfoServiceImpl getMovieInfoService;
    private final photoUriInfo photoUriinfo;

    @GetMapping
    public String goMovie(Model model){
        model.addAttribute("movieInfo",new movieInfo());
        return "MoviePage/MoviePageMain";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/viewOneMovie")
    @ResponseBody
    public movieInfo MovieView(movieInfo movieinfo, Model model){
        movieInfo movie = getMovieInfoService.getMovie(movieinfo);

        photoUriinfo.setPhotoUri(movie.getPhotoUri());
        photoUriinfo.setPhotoOriName(movie.getPhotoOriName());

        photoUriInfo photoUriInfo = uploadService.showPhoto(photoUriinfo);
        ClassPathResource resource = new ClassPathResource("/moviePhoto/"+photoUriInfo.getPhotoOriName());

        Path path1 = Paths.get(resource.getPath());

        movie.setPhotoUri(path1.toString());
        return movie;
    }
}
