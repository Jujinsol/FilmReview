package movieReview.review.repository.GetMovieInfo;

import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class getMovieRepositoryImpl implements getMovieRepository{

    private final EntityManager em;

    @Autowired
    public getMovieRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<JpaMovieInfo> findAll() {
        return em.createQuery("select m from JpaMovieInfo m", JpaMovieInfo.class)
                .getResultList();
    }

    @Override
    public List<JpaMovieInfo> findMovie(movieInfo movieinfo) {
        return em.createQuery("select m from JpaMovieInfo m where m.movieName= :movieName",JpaMovieInfo.class)
                .setParameter("movieName",movieinfo.getMovieName())
                .getResultList();
    }

    @Override
    public movieInfo EachMovieInfo(movieInfo movieInfo) {

    }
}
