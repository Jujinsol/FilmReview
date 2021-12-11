package movieReview.review.service.MainPage.MainPageProxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.service.MainPage.MainPageService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
public class cacheProxy implements MainPageService {

    private final MainPageService target;
    private final ThreadLocal<Page> cacheValue = new ThreadLocal<>();


    @Override
    public Page<JpaMovieInfo> findAll(int page) {

        if(cacheValue.get() == null){
            log.info("프록시 캐시에 값 없음");
            cacheValue.set(target.findAll(page));
        }

        return cacheValue.get();
    }


    public void cacheReset(){
        log.info("캐시값 초기화");
        cacheValue.set(null);
    }

}
