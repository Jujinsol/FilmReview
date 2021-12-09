package movieReview.review.service.MainPage.MainPageProxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.repository.MainPage.MainPageRepository;
import movieReview.review.service.MainPage.MainPageService;
import movieReview.review.service.RedisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class cacheProxy implements MainPageService {

    private final MainPageRepository targetRepository;
    private final ThreadLocal<Page> cacheValue = new ThreadLocal<>();


    @Override
    public Page<JpaMovieInfo> findAll(int page) {

        if(cacheValue.get() == null){
            log.info("프록시 캐시에 값 없음");
            PageRequest paging = PageRequest.of(page, 20);
            cacheValue.set(targetRepository.findAll(paging));
        }
        return cacheValue.get();
    }

    public void cacheReset(){
        log.info("캐시값 초기화");
        cacheValue.set(null);
    }
}
