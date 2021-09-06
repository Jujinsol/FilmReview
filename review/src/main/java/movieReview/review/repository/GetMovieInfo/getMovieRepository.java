package movieReview.review.repository.GetMovieInfo;

import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;

import java.util.ArrayList;
import java.util.List;


public interface getMovieRepository {
    List<JpaMovieInfo> findAll(); // 영화정보 가져오기
    List<JpaMovieInfo> findMovie(movieInfo movieinfo);
}
