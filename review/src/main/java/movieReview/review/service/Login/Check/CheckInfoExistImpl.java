package movieReview.review.service.Login.Check;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
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
    public userInfo checkUser(String id, Integer password){
        Optional<userInfo> userInfoCheck = Optional.ofNullable((loginRepository.userLoginCheck(setuser(id, password), sql.get("userSql"))));
        log.info("id={},password={},",id,password);
        log.info("userInfoCheck={},",userInfoCheck);
        if(userInfoCheck.isEmpty()){
            // 둘다 틀리게 작성했을 경우 ( 테이블에 아예 존재하지 않는 경우 )
            return null;
        }else{
            userInfo userInfo = userInfoCheck.get();
            return userInfo;
        }
    }

    @Override
    public mangerInfo checkManger(String id, Integer password){
        Optional<mangerInfo> mangerInfoCheck = Optional.ofNullable(loginRepository.mangerLoginCheck(setmanger(id, password), sql.get("masterSql")));
        if(mangerInfoCheck.isEmpty()){
            // 둘다 틀리게 작성했을 경우 ( 테이블에 아예 존재하지 않는 경우 )
            return null;
        }else{
            mangerInfo mangerInfo = mangerInfoCheck.get();
            return mangerInfo;
        }

    }

    @Override
    public userInfo userIdCheck(String id) {
        Optional<userInfo> userIdCheck = Optional.ofNullable(loginRepository.userIdCheck(setuser(id,null), sql.get("checkUserId")));
        if(userIdCheck.isEmpty()){
            return null;
        }else{
            userInfo userInfo = userIdCheck.get();
            return userInfo;
        }
    }

    @Override
    public mangerInfo mangerIdCheck(String id) {
        Optional<mangerInfo> mangerIdCheck = Optional.ofNullable(loginRepository.mangerIdCheck(setmanger(id, null), sql.get("checkMasterId")));
        if(mangerIdCheck.isEmpty()){
            return null;
        }else{
            mangerInfo mangerInfo = mangerIdCheck.get();
            return mangerInfo;
        }
    }

    @Override
    public userInfo userPwChcek(Integer password) {
        Optional<userInfo> userPwCheck = Optional.ofNullable(loginRepository.userPwCheck(setuser(null, password), sql.get("checkUserPassword")));
        if(userPwCheck.isEmpty()){
            return null;
        }else{
            userInfo userInfo = userPwCheck.get();
            return userInfo;
        }
    }

    @Override
    public mangerInfo mangerPwCheck(Integer password) {
        Optional<mangerInfo> mangerPwCheck = Optional.ofNullable(loginRepository.mangerPwCheck(setmanger(null,password),sql.get("checkMasterPassword")));
        if(mangerPwCheck.isEmpty()){
            return null;
        }else{
            mangerInfo mangerInfo = mangerPwCheck.get();
            return mangerInfo;
        }
    }

    public userInfo setuser(String id, Integer password){
        userInfo userinfo = new userInfo();
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
    public mangerInfo setmanger(String id, Integer password){
        mangerInfo mangerinfo = new mangerInfo();
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
