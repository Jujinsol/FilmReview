package movieReview.review.service.reviewFunction.GetAllMyReviews;

import movieReview.review.Domain.ReviewInfo.ReviewInfo;

public interface ChangeOriginalMovieName {
    String[] ChangeMovieName(String id) throws ClassNotFoundException;    // 영화제목 가져와서 원본영화제목으로 변경시켜주는 작업을 한번에 해주는 메서드
}
