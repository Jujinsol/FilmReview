package movieReview.review.repository.Join;

import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;

public interface joinRepository {
    int createUser(userInfo userinfo); //사용자 회원가입
    int createManger(mangerInfo mangerinfo); //관리자 회원가입
    userInfo selectMyinfo(userInfo userinfo); //사용자 정보 확인
    mangerInfo selectMangerinfo(mangerInfo mangerinfo); //관리자 정보 확인
    int updateMyinfo(userInfo userinfo); //사용자 비밀번호 변경
    int updateMangInfo(mangerInfo mangerinfo);
    int delete(userInfo userinfo); // 사용자정보 삭제
    int deleteManger(mangerInfo mangerinfo); // 관리자정보 삭제

}
