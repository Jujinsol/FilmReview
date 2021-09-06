package movieReview.review.service.GetMovieInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class getMovieInfoServiceImpl implements getMovieInfoService {
    private final getMovieRepository getMovieRepository;

    @Override
    public List<JpaMovieInfo> getMovie(movieInfo movieinfo) {
        return getMovieRepository.findMovie(movieinfo);
    }
}
