package movieReview.review.service.Login;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import org.springframework.stereotype.Service;

public interface LoginService {
    userInfo userLogin(userInfo userinfo);
    mangerInfo mangerLogin(mangerInfo mangerinfo);
}
