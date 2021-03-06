package movieReview.review.service.GetMovieInfo;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface getMovieInfoService {
    List<JpaMovieInfo> getMovie(movieInfo movieinfo); // 영화이름을 검색해서 모든 영화정보 가져옴
    movieInfo EachMovie(movieInfo movieinfo); // 영화클릭시 나오는 영화정보
    String getCookie(String photoOriName,
                     movieInfo movieinfo,
                     ReviewInfo reviewInfo,
                     Model model) throws UnsupportedEncodingException;
}
