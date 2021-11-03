package movieReview.review.service.reviewFunction.GetAllMyReviews;

import movieReview.review.Domain.ReviewInfo.ReviewInfo;

import java.util.List;

public interface GetAllMyReviews {
    List<ReviewInfo> getAllMyReviews(ReviewInfo reviewInfo) throws ClassNotFoundException; // 내가 작성했던 모든 리뷰 가져오기
}
