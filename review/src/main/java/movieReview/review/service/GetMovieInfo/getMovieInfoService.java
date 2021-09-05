package movieReview.review.service.GetMovieInfo;

import movieReview.review.dto.MovieInfo.movieInfo;

public interface getMovieInfoService {
    movieInfo getMovie(movieInfo movieinfo); // 영화이름을 검색해서 영화정보 가져옴
}
