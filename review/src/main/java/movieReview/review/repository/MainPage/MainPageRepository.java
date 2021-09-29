package movieReview.review.repository.MainPage;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface MainPageRepository extends PagingAndSortingRepository<JpaMovieInfo,Integer> {
}
