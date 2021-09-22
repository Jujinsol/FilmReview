package movieReview.review.SpringConfig;

import lombok.RequiredArgsConstructor;
import movieReview.review.SpringInterceptor.LoginInterceptor;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import movieReview.review.repository.GetMovieInfo.getMovieRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {
    private final EntityManager em;
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/css/**","/js/**","/moviePhoto/**",
                        "/Join/**","/MainPage/**","/Login/**","/oneMovie/**",
                        "/webjars/**","/MoviePage/**","/EachMovie/**");
    }

    @Bean
    public getMovieRepository getMovieRepository(){
        return new getMovieRepositoryImpl(em);
    }


}
