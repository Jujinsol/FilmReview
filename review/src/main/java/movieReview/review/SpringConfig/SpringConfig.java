package movieReview.review.SpringConfig;

import lombok.RequiredArgsConstructor;
import movieReview.review.SpringInterceptor.LoginInterceptor;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import movieReview.review.repository.GetMovieInfo.getMovieRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
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
                .addPathPatterns("/Upload/**","/MyPage/**","/DeleteMovie/**","/EachMovie/reviewUpload","/EachMovie/reviewDelete","/logout")
                .excludePathPatterns("/");
    }

    @Bean
    public getMovieRepository getMovieRepository(){
        return new getMovieRepositoryImpl(em);
    }

    @Bean // form에서 지원하지않는 delete나 put등의 http 메서드를 사용하기 위해서, 필터로 잡아준다.
    public FilterRegistrationBean httpMethodFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new HiddenHttpMethodFilter());
        filter.setName("httpMethodFilter");
        filter.addUrlPatterns("/*");
        return filter;
    }

}
