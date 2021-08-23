package movieReview.review.service;

import movieReview.review.dto.userInfo;

public interface joinService {
    int join(String email, String id, int password, Integer mangerNum); //회원가입
    userInfo myInfo(String id); //내정보 조회
    int update(String id, int password); //내정보 수정
    int delete(String id, int password); //회원탈퇴
}
