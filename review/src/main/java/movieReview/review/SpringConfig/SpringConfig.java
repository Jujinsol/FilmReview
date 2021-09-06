package movieReview.review.SpringConfig;

import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import movieReview.review.repository.GetMovieInfo.getMovieRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public getMovieRepository getMovieRepository(){
        return new getMovieRepositoryImpl(em);
    }
}
