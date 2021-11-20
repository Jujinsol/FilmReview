package movieReview.review.service.Login;

import movieReview.review.Domain.Login.loginForm;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;

public interface LoginService {
    loginUserInfo userLogin(loginUserInfo userinfo);
    loginMangerInfo mangerLogin(loginMangerInfo mangerinfo);
    int noSuchuser(String id);
    int FirstCheck(String id); // 사용자인지 관리자인지 판별
    int loginResult(loginForm form); // 로그인로직 실행
}
