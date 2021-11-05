package movieReview.review.service.reviewFunction.GetAllMyReviews;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ChangeOriginalMovieNameImpl implements ChangeOriginalMovieName {
    private final GetAllMyReviews getAllMyReviews; // 모든리뷰정보 추출하기위한 서비스
    private final getMovieRepository getMovieInfo; // 사진이름으로 영화이름 추출하기위한 리포지토리

    @Autowired
    public ChangeOriginalMovieNameImpl(GetAllMyReviews getAllMyReviews, getMovieRepository getMovieInfo) {
        this.getMovieInfo = getMovieInfo;
        this.getAllMyReviews = getAllMyReviews;
    }


    public String[] ChangeMovieName(String id) throws ClassNotFoundException {
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setReviewUser(id);
        List<ReviewInfo> allMyReviews = getAllMyReviews.getAllMyReviews(reviewInfo.getReviewUser());
        String[] OriMovieNames = new String[allMyReviews.size()];

        for(int i = 0; i<allMyReviews.size(); i++){
            movieInfo movieInfo = new movieInfo();
            ReviewInfo getReview = allMyReviews.get(i);
            movieInfo.setPhotoOriName(getReview.getPhotoOriName());

            JpaMovieInfo OriMovieInfo = getMovieInfo.EachMovieInfo(movieInfo); // 영화제목 가져옴
            OriMovieNames[i] = OriMovieInfo.getMovieName();
        }

        return OriMovieNames;
    }


}
