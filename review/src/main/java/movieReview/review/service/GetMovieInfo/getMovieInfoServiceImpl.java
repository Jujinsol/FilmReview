package movieReview.review.service.GetMovieInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class getMovieInfoServiceImpl implements getMovieInfoService {
    private final getMovieRepository getMovieRepository;

    @Override
    public List<JpaMovieInfo> getMovie(movieInfo movieinfo) {
        return getMovieRepository.findMovie(movieinfo);
    }

    @Override
    public movieInfo EachMovie(movieInfo movieinfo) {
        JpaMovieInfo jpaMovieInfo = getMovieRepository.EachMovieInfo(movieinfo);
        return movieinfo.Builder()
                .setMovieName(jpaMovieInfo.getMovieName())
                .setPhotoUri(jpaMovieInfo.getPhotoUri())
                .setOpenYear(jpaMovieInfo.getOpenYear())
                .setStoryLine(jpaMovieInfo.getStoryLine())
                .setDirectorName(jpaMovieInfo.getDirectorName())
                .setTrailerCode(jpaMovieInfo.getTrailerCode())
                .build();
    }

}
