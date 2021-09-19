package movieReview.review.service.reviewFunction.reviewUploadServie;

import movieReview.review.dto.ReviewInfo.JpaRevieTab;
import movieReview.review.dto.ReviewInfo.ReviewInfo;

import java.util.List;

public interface ReviewUploadService {
    int reviewUpload(ReviewInfo reviewInfo); // 영화 리뷰 등록
    int deleteReview(ReviewInfo reviewInfo); // 영화 리뷰 삭제
    List<JpaRevieTab> selectAllReview(ReviewInfo reviewInfo) throws ClassNotFoundException; // 모든영화리뷰 가지고오기
}
