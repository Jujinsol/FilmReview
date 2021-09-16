package movieReview.review.SpringConfig;

import lombok.RequiredArgsConstructor;
import movieReview.review.SpringInterceptor.LoginInterceptor;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import movieReview.review.repository.GetMovieInfo.getMovieRepositoryImpl;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepository;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {
    private final EntityManager em;
    private final JdbcTemplate JdbcTemplate;
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/css/**","/js/**","/moviePhoto/**"
                        ,"/Join/**","/MainPage/**","/Login/**","/oneMovie/**","/MoviePage/**","/EachMovie/**");
    }

    @Bean
    public getMovieRepository getMovieRepository(){
        return new getMovieRepositoryImpl(em);
    }

    @Bean
    public ReviewFunctionRepository findMovieRepository(){
        return new ReviewFunctionRepositoryImpl(JdbcTemplate, em);
    }
}