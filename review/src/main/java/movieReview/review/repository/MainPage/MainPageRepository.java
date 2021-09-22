package movieReview.review.repository.MainPage;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainPageRepository extends PagingAndSortingRepository<JpaMovieInfo,Integer> {
}
