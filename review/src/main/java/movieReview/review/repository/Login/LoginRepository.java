package movieReview.review.repository.Login;

import movieReview.review.dto.Login.loginMangerInfo;
import movieReview.review.dto.Login.loginUserInfo;

public interface LoginRepository {
    loginUserInfo userLoginCheck(loginUserInfo userinfo, String sql); //유저로그인 전체 정보 확인
    loginMangerInfo mangerLoginCheck(loginMangerInfo mangerinfo, String sql); //관리자로그인 전체 정보 확인

    loginUserInfo userIdCheck(loginUserInfo userinfo, String sql);
    loginMangerInfo mangerIdCheck(loginMangerInfo mangerinfo, String sql);

    loginUserInfo userPwCheck(loginUserInfo userinfo, String sql);
    loginMangerInfo mangerPwCheck(loginMangerInfo mangerinfo, String sql);
}
