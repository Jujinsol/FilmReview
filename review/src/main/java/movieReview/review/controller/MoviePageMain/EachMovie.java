package movieReview.review.controller.MoviePageMain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/EachMovie")
@RequiredArgsConstructor
public class EachMovie {

    @GetMapping("/getMovieInfo")
    public String EachMovieInfo(){
        return "MoviePage/EachMovieInfo";
    }
}
