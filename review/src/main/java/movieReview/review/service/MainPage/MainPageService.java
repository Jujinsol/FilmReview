package movieReview.review.service.MainPage;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;


public interface MainPageService {
    @Query("Select JpaMovieInfo.photoUri, JpaMovieInfo.movieName from JpaMovieInfo")
    Page<JpaMovieInfo> movieList(int page);
}
