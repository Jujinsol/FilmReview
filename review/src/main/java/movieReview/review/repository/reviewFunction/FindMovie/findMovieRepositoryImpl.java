package movieReview.review.repository.reviewFunction.FindMovie;

import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class findMovieRepositoryImpl implements findMovieRepository {

    private final EntityManager em;

    @Autowired
    public findMovieRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public int insertReview(ReviewInfo reviewinfo) {
        em.persist(reviewinfo);

    }
}
