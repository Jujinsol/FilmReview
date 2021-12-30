package movieReview.review.controller.SearchFunc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Session.SessionConst;
import movieReview.review.service.GetMovieInfo.getMovieInfoService;
import movieReview.review.service.Upload.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oneMovie")
// 모든영화 검색결과 조회
public class MoviePage {
    private final UploadService uploadService;
    private final getMovieInfoService getMovieInfoService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/viewOneMovie")
    @ResponseBody
    public List<JpaMovieInfo> MovieView(movieInfo movieinfo) {
        List<JpaMovieInfo> jpaMovieInfos = new ArrayList<>();
        List<JpaMovieInfo> uri = new ArrayList<>();
        List<JpaMovieInfo> movie = getMovieInfoService.getMovie(movieinfo);
        for (int i = 0; i < movie.size(); i++) {
            uri.add(uploadService.makeUri(movie.get(i)));
            jpaMovieInfos.add(uri.get(i));
        }
        return jpaMovieInfos;
    }

    @PostMapping(value = "/newPage")
    public String newPage(@RequestParam("movieJsonData") String jsonResult,
                          Model model,
                          @SessionAttribute(required = false, value = SessionConst.LoginId) String id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> jsonMovieSearchlist = objectMapper.readValue(jsonResult, List.class);

        model.addAttribute("SessionId",id);
        model.addAttribute("movieInfo",new movieInfo());
        model.addAttribute("SerachResult",jsonMovieSearchlist);
        return "/SerachPage/MovieSerachPage";
    }


}
