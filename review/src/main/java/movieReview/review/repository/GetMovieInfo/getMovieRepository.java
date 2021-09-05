package movieReview.review.repository.GetMovieInfo;

import movieReview.review.dto.MovieInfo.movieInfo;

public interface getMovieRepository {
    movieInfo getMovieInfo(movieInfo movieinfo); // 영화정보 가져오기
}
