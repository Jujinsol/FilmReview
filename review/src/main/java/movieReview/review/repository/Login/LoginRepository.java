package movieReview.review.repository.Login;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;

public interface LoginRepository {
    userInfo userLoginCheck(userInfo userinfo, String sql); //유저로그인 전체 정보 확인
    mangerInfo mangerLoginCheck(mangerInfo mangerinfo, String sql); //관리자로그인 전체 정보 확인

    userInfo userIdCheck(userInfo userinfo, String sql);
    mangerInfo mangerIdCheck(mangerInfo mangerinfo, String sql);

    userInfo userPwCheck(userInfo userinfo, String sql);
    mangerInfo mangerPwCheck(mangerInfo mangerinfo, String sql);
}
