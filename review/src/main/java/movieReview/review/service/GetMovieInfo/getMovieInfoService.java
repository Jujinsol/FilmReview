package movieReview.review.service.GetMovieInfo;

import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface getMovieInfoService {
    List<JpaMovieInfo> getMovie(movieInfo movieinfo); // 영화이름을 검색해서 영화정보 가져옴
}
