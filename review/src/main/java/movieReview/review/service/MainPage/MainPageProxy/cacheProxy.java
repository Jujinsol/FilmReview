package movieReview.review.service.MainPage.MainPageProxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.repository.MainPage.MainPageRepository;
import movieReview.review.service.MainPage.MainPageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        }else if(false){
            // 관리자가 업로드했을때 동시에 cacheValue 초기화시켜줘야됨.
        }

        return cacheValue.get();

    }
}
