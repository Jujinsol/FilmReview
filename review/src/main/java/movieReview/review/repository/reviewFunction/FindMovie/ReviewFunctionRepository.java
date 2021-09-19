package movieReview.review.repository.reviewFunction.FindMovie;


import movieReview.review.dto.ReviewInfo.JpaRevieTab;
import movieReview.review.dto.ReviewInfo.ReviewInfo;

import java.util.List;

public interface ReviewFunctionRepository {
    int insertReview(ReviewInfo reviewinfo); // 리뷰등록
    int deleteReview(ReviewInfo reviewInfo); // 리뷰삭제
    List<JpaRevieTab> selectReview(ReviewInfo reviewInfo) throws ClassNotFoundException; // 영화별 리뷰 전체 불러오기

}
