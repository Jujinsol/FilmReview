package movieReview.review.service.Login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.Login.loginMangerInfo;
import movieReview.review.dto.Login.loginUserInfo;
import movieReview.review.service.Join.checkMangerOrUser;
import movieReview.review.service.Login.Check.CheckInfoExistImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{
    private final checkMangerOrUser checkMangerOrUser;
    private final CheckInfoExistImpl checkInfoExist;

    //사용자인지 관리자인지 아예없는회원인지 판단하는 메서드
    public int FirstCheck(String id){
        return checkMangerOrUser.check(id);
    }

    @Override
    public loginUserInfo userLogin(loginUserInfo userinfo) {
        Optional<loginUserInfo> userIdCheck = Optional.ofNullable(checkInfoExist.userIdCheck(userinfo.getId()));
        Optional<loginUserInfo> userPwCheck = Optional.ofNullable(checkInfoExist.userPwChcek(userinfo.getPassword()));
        Optional<loginUserInfo> userCheck = Optional.ofNullable(checkInfoExist.checkUser(userinfo.getId(), userinfo.getPassword()));

        if(!userIdCheck.isEmpty()){
            if(!userPwCheck.isEmpty()){
                if(!userCheck.isEmpty()){
                    //로그인 성공
                    return userinfo;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public loginMangerInfo mangerLogin(loginMangerInfo mangerinfo) {
        Optional<loginMangerInfo> mangerIdCheck = Optional.ofNullable(checkInfoExist.mangerIdCheck(mangerinfo.getId()));
        Optional<loginMangerInfo> mangerPwCheck = Optional.ofNullable(checkInfoExist.mangerPwCheck(mangerinfo.getPassword()));
        Optional<loginMangerInfo> mangerCheck = Optional.ofNullable(checkInfoExist.checkManger(mangerinfo.getId(), mangerinfo.getPassword()));

        if(!mangerIdCheck.isEmpty()){
            if(!mangerPwCheck.isEmpty()){
                if(!mangerCheck.isEmpty()){
                    //로그인 성공
                    return mangerinfo;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public int noSuchuser(String id) {
        return 0;
    }
}
