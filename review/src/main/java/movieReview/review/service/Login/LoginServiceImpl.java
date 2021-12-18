package movieReview.review.service.Login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginForm;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.service.Join.checkMangerOrUser;
import movieReview.review.service.Login.Check.CheckInfoExist;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final checkMangerOrUser checkMangerOrUser;
    private final CheckInfoExist checkInfoExist;

    //사용자인지 관리자인지 아예없는회원인지 판단하는 메서드
    @Override
    public int FirstCheck(String id) {
        return checkMangerOrUser.check(id);
    }

    @Override
    public int loginResult(loginForm form) {
        switch (FirstCheck(form.getId())) {
            case 0: // 아예없을때
                return 2;
            case 1: // 사용자
                loginUserInfo user = new loginUserInfo();
                user.setId(form.getId());
                user.setPassword(form.getPassword());
                loginUserInfo userResult = userLogin(user);

                if (userResult != null) {
                    return 1;
                } else {
                    return 0;
                }

            case 2: // 관리자
                loginMangerInfo man = new loginMangerInfo();
                man.setId(form.getId());
                man.setPassword(form.getPassword());

                loginMangerInfo manResult = mangerLogin(man);

                if (manResult != null) {
                    return 1;
                } else {
                    return 0;
                }
        }
        return 0;
    }

    @Override
    public loginUserInfo userLogin(loginUserInfo userinfo) {
        loginDto userCheck = checkInfoExist.checkUser(userinfo.getId(), userinfo.getPassword());
        return userCheck == null ? null : userinfo;
    }

    @Override
    public loginMangerInfo mangerLogin(loginMangerInfo mangerinfo) {
        loginDto mangerCheck = checkInfoExist.checkManger(mangerinfo.getId(), mangerinfo.getPassword());
        return mangerCheck == null ? null : mangerinfo;
    }
}
