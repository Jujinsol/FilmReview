package movieReview.review.service.MainPage;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MainPageService {
    Page<JpaMovieInfo> findAll(int page);
}
