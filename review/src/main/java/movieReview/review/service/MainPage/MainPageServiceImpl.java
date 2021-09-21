package movieReview.review.service.MainPage;

import lombok.RequiredArgsConstructor;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.repository.MainPage.MainPageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {
    private final MainPageRepository repository;


    @Override
    public Page<JpaMovieInfo> movieList(int page) {
        PageRequest paging = PageRequest.of(1, 20, Sort.Direction.DESC,"idx");
        return repository.findAll(paging);
    }
}
