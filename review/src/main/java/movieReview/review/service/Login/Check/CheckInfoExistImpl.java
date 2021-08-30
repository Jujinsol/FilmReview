package movieReview.review.service.Login.Check;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.Login.loginMangerInfo;
import movieReview.review.dto.Login.loginUserInfo;
import movieReview.review.repository.Login.LoginRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CheckInfoExistImpl implements CheckInfoExist{
    private LoginRepositoryImpl loginRepository;
    private Map<String,String> sql = new HashMap<>();

    @Autowired
    public CheckInfoExistImpl(LoginRepositoryImpl loginRepository){
        this.loginRepository = loginRepository;

        sql.put("userSql","SELECT userId, userPw,userEmail from user where userId=? AND userPw =?;");
        sql.put("masterSql","select masterId, number, pw, email from master where masterId=? And pw=?");

        sql.put("checkUserId","SELECT userId, userPw, userEmail from user where userId=?");
        sql.put("checkMasterId","select masterId, number, pw, email from master where masterId=?");

        sql.put("checkUserPassword","SELECT userId, userPw, userEmail from user where userPw=?");
        sql.put("checkMasterPassword","select masterId, number, pw, email from master where pw=?");
    }

    @Override
    public loginUserInfo checkUser(String id, Integer password){
        Optional<loginUserInfo> userInfoCheck = Optional.ofNullable((loginRepository.userLoginCheck(setuser(id, password), sql.get("userSql"))));
        log.info("id={},password={},",id,password);
        log.info("userInfoCheck={},",userInfoCheck);
        if(userInfoCheck.isEmpty()){
            // 둘다 틀리게 작성했을 경우 ( 테이블에 아예 존재하지 않는 경우 )
            return null;
        }else{
            loginUserInfo userInfo = userInfoCheck.get();
            return userInfo;
        }
    }

    @Override
    public loginMangerInfo checkManger(String id, Integer password){
        Optional<loginMangerInfo> mangerInfoCheck = Optional.ofNullable(loginRepository.mangerLoginCheck(setmanger(id, password), sql.get("masterSql")));
        if(mangerInfoCheck.isEmpty()){
            // 둘다 틀리게 작성했을 경우 ( 테이블에 아예 존재하지 않는 경우 )
            return null;
        }else{
            loginMangerInfo mangerInfo = mangerInfoCheck.get();
            return mangerInfo;
        }

    }

    @Override
    public loginUserInfo userIdCheck(String id) {
        Optional<loginUserInfo> userIdCheck = Optional.ofNullable(loginRepository.userIdCheck(setuser(id,null), sql.get("checkUserId")));
        if(userIdCheck.isEmpty()){
            return null;
        }else{
            loginUserInfo userInfo = userIdCheck.get();
            return userInfo;
        }
    }

    @Override
    public loginMangerInfo mangerIdCheck(String id) {
        Optional<loginMangerInfo> mangerIdCheck = Optional.ofNullable(loginRepository.mangerIdCheck(setmanger(id, null), sql.get("checkMasterId")));
        if(mangerIdCheck.isEmpty()){
            return null;
        }else{
            loginMangerInfo mangerInfo = mangerIdCheck.get();
            return mangerInfo;
        }
    }

    @Override
    public loginUserInfo userPwChcek(Integer password) {
        Optional<loginUserInfo> userPwCheck = Optional.ofNullable(loginRepository.userPwCheck(setuser(null, password), sql.get("checkUserPassword")));
        if(userPwCheck.isEmpty()){
            return null;
        }else{
            loginUserInfo userInfo = userPwCheck.get();
            return userInfo;
        }
    }

    @Override
    public loginMangerInfo mangerPwCheck(Integer password) {
        Optional<loginMangerInfo> mangerPwCheck = Optional.ofNullable(loginRepository.mangerPwCheck(setmanger(null,password),sql.get("checkMasterPassword")));
        if(mangerPwCheck.isEmpty()){
            return null;
        }else{
            loginMangerInfo mangerInfo = mangerPwCheck.get();
            return mangerInfo;
        }
    }

    public loginUserInfo setuser(String id, Integer password){
        loginUserInfo userinfo = new loginUserInfo();
        if(id==null){
            userinfo.setPassword(password);
            return userinfo;
        }else if(password==null){
            userinfo.setId(id);
            return userinfo;
        }else{
            userinfo.setId(id);
            userinfo.setPassword(password);
            return userinfo;
        }
    }
    public loginMangerInfo setmanger(String id, Integer password){
        loginMangerInfo mangerinfo = new loginMangerInfo();
        if(id==null){
            mangerinfo.setPassword(password);
            return mangerinfo;
        }else if(password==null){
            mangerinfo.setId(id);
            return mangerinfo;
        }else{
            mangerinfo.setId(id);
            mangerinfo.setPassword(password);
            return mangerinfo;
        }
    }
}
