package movieReview.review.service.MainPage;

import lombok.RequiredArgsConstructor;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.repository.MainPage.MainPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {
    private final MainPageRepository repository;

    @Override
    public Page<JpaMovieInfo> findAll(int page) {
        // 불러올페이지에서 , 받아온 page에 최대 20개까지 정렬
        PageRequest paging = PageRequest.of(page, 20);
        return repository.findAll(paging);
    }

}
