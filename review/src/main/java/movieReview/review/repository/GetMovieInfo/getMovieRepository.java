package movieReview.review.repository.GetMovieInfo;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;

import java.util.List;


public interface getMovieRepository {
    List<JpaMovieInfo> findMovie(movieInfo movieinfo); // 영화제목으로 영화정보 가지고오기
    JpaMovieInfo EachMovieInfo(movieInfo movieInfo); // 각영화 클릭하면 나오는 모든영화정보 가지고오기 - 영화사진이름으로 가지고오기
}
