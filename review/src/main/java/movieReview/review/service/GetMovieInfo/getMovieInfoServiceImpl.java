package movieReview.review.service.GetMovieInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
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
        movieinfo.setMovieName(jpaMovieInfo.getMovieName());
        movieinfo.setPhotoUri(jpaMovieInfo.getPhotoUri());
        movieinfo.setOpenYear(jpaMovieInfo.getOpenYear());
        movieinfo.setStoryLine(jpaMovieInfo.getStoryLine());
        movieinfo.setDirectorName(jpaMovieInfo.getDirectorName());
        movieinfo.setTrailerCode(jpaMovieInfo.getTrailerCode());
        return movieinfo;
    }

}
