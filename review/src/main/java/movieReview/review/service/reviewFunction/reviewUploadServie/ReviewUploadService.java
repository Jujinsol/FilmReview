package movieReview.review.service.reviewFunction.reviewUploadServie;

import movieReview.review.dto.ReviewInfo.ReviewInfo;

public interface ReviewUploadService {
    int reviewUpload(ReviewInfo reviewInfo); // 영화 리뷰 등록
    int deleteReview(ReviewInfo reviewInfo); // 영화 리뷰 삭제
}
