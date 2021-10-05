package movieReview.review.service.reviewFunction.UserDuplicateCheck;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserDuplicateCheckService {
    boolean DuplicateCheck(String id, String reviewUsers) throws JsonProcessingException; // 리뷰단사람중에 지금로그인한 사람이 존재하는지 확인
}
