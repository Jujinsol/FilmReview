package movieReview.review.service.GetMovieInfo;

import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;

import java.util.List;

public interface getMovieInfoService {
    List<JpaMovieInfo> getMovie(movieInfo movieinfo); // 영화이름을 검색해서 영화정보 가져옴
    movieInfo EachMovie(movieInfo movieinfo); // 리뷰용 영화정보
}
