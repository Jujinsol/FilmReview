package movieReview.review.service.Login;

import movieReview.review.dto.Login.loginMangerInfo;
import movieReview.review.dto.Login.loginUserInfo;

public interface LoginService {
    loginUserInfo userLogin(loginUserInfo userinfo);
    loginMangerInfo mangerLogin(loginMangerInfo mangerinfo);
    int noSuchuser(String id);
    int FirstCheck(String id); // 사용자인지 관리자인지 판별
}
