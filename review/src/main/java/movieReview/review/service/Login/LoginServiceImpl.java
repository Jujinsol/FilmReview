package movieReview.review.service.Login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
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
    public userInfo userLogin(userInfo userinfo) {
        Optional<userInfo> userIdCheck = checkInfoExist.userIdCheck(userinfo.getId());
        Optional<userInfo> userPwCheck = checkInfoExist.userPwChcek(userinfo.getPassword());
        Optional<userInfo> userCheck = checkInfoExist.checkUser(userinfo.getId(), userinfo.getPassword());

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
    public mangerInfo mangerLogin(mangerInfo mangerinfo) {
        Optional<mangerInfo> mangerIdCheck = checkInfoExist.mangerIdCheck(mangerinfo.getId());
        Optional<mangerInfo> mangerPwCheck = checkInfoExist.mangerPwCheck(mangerinfo.getPassword());
        Optional<mangerInfo> mangerCheck = checkInfoExist.checkManger(mangerinfo.getId(), mangerinfo.getPassword());

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
