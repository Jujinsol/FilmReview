package movieReview.review.repository.Login;

import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;

public interface LoginRepository {
    loginDto userLoginCheck(loginDto dto, String sql); //유저로그인 전체 정보 확인
    loginDto mangerLoginCheck(loginDto dto, String sql); //관리자로그인 전체 정보 확인

    loginDto userIdCheck(loginDto dto, String sql);
    loginDto mangerIdCheck(loginDto dto, String sql);

    loginDto userPwCheck(loginDto dto, String sql);
    loginDto mangerPwCheck(loginDto dto, String sql);
}
