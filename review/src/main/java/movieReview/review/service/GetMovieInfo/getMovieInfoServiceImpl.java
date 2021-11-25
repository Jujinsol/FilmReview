package movieReview.review.service.GetMovieInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @Override
    public String getCookie(String photoOriName, movieInfo movieinfo, ReviewInfo reviewInfo, Model model) throws UnsupportedEncodingException {
        String originPhotoUri = photoOriName.substring(10); // 원본사진이름 추출
        movieinfo.setPhotoOriName(originPhotoUri); // 영화정보 가져오기위해서 set
        reviewInfo.setPhotoOriName(originPhotoUri); // 영화별 리뷰 전부다 가져오기위해서 set

        model.addAttribute("photoPath", originPhotoUri); // 사진경로 처리후 모델에 담아서 전송

        return URLEncoder.encode(originPhotoUri, "utf-8");// 한글파일 쿠키저장위해 인코딩
    }

}
