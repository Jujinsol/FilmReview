package movieReview.review.service.GetMovieInfo;

import lombok.RequiredArgsConstructor;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class getMovieInfoServiceImpl implements getMovieInfoService{
    private final getMovieRepository getMovieRepository;

    @Override
    public movieInfo getMovie(movieInfo movieinfo) {
        return getMovieRepository.getMovieInfo(movieinfo);
    }
}
