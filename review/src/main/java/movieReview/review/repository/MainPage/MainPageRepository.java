package movieReview.review.repository.MainPage;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainPageRepository extends PagingAndSortingRepository<JpaMovieInfo,String> {
}
