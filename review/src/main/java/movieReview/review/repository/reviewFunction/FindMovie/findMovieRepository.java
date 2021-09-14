package movieReview.review.repository.reviewFunction.FindMovie;


import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.ReviewInfo;

public interface findMovieRepository {
    int insertReview(ReviewInfo reviewinfo); // 리뷰등록
    int deleteReview(ReviewInfo reviewInfo); // 리뷰삭제
}
