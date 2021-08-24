package movieReview.review.repository.Join;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;

public interface joinRepository {
    int createUser(userInfo userinfo);
    int createManger(mangerInfo mangerinfo);
    userInfo selectMyinfo(userInfo userinfo);
    mangerInfo selectMangerinfo(mangerInfo mangerinfo);
    int updateMyinfo(userInfo userinfo);
    int delete(userInfo userinfo);
    int deleteManger(mangerInfo mangerinfo);

}
