package movieReview.review.repository.GetMovieInfo;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
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
    public List<JpaMovieInfo> findMovie(movieInfo movieinfo) {
        return em.createQuery("select m from JpaMovieInfo m where m.movieName= :movieName",JpaMovieInfo.class)
                .setParameter("movieName",movieinfo.getMovieName())
                .getResultList();
    }

    @Override
    public JpaMovieInfo EachMovieInfo(movieInfo movieInfo) {
        return em.createQuery("select m from JpaMovieInfo m where m.photoOriName= :photoOriName",JpaMovieInfo.class)
                .setParameter("photoOriName",movieInfo.getPhotoOriName())
                .getSingleResult();
    }
}
