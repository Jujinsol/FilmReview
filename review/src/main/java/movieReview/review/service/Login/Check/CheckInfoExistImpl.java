package movieReview.review.service.Login.Check;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.repository.Login.LoginRepository;
import movieReview.review.repository.Login.LoginRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CheckInfoExistImpl implements CheckInfoExist{
    private LoginRepository loginRepository;
    private Map<String,String> sql =new HashMap<>();
    @Autowired
    public CheckInfoExistImpl(LoginRepositoryImpl loginRepository) {

        this.loginRepository = loginRepository;

        sql.put("userSql","SELECT userId, userPw,userEmail from user where userId=? AND userPw =?;");
        sql.put("masterSql","select masterId, number, pw, email from master where masterId=? And pw=?");

        sql.put("checkUserId","SELECT userId, userPw, userEmail from user where userId=?");
        sql.put("checkMasterId","select masterId, number, pw, email from master where masterId=?");

        sql.put("checkUserPassword","SELECT userId, userPw, userEmail from user where userPw=?");
        sql.put("checkMasterPassword","select masterId, number, pw, email from master where pw=?");

    }

    @Override
    public loginDto checkUser(String id, Integer password){
        Optional<loginDto> userInfoCheck =
                Optional.ofNullable((loginRepository.userLoginCheck(setInfo(id, password), sql.get("userSql"))));
        if(userInfoCheck.isEmpty()){
            // 둘다 틀리게 작성했을 경우 ( 테이블에 아예 존재하지 않는 경우 )
            return null;
        }else{
            loginDto userInfo = userInfoCheck.get();
            return userInfo;
        }
    }

    @Override
    public loginDto checkManger(String id, Integer password){

        Optional<loginDto> mangerInfoCheck =
                Optional.ofNullable(loginRepository.mangerLoginCheck(setInfo(id, password), sql.get("masterSql")));
        if(mangerInfoCheck.isEmpty()){
            // 둘다 틀리게 작성했을 경우 ( 테이블에 아예 존재하지 않는 경우 )
            return null;
        }else{
            loginDto mangerInfo = mangerInfoCheck.get();
            return mangerInfo;
        }

    }

    @Override
    public loginDto userIdCheck(String id) {
        Optional<loginDto> userIdCheck =
                Optional.ofNullable(loginRepository.userIdCheck(setInfo(id,null), sql.get("checkUserId")));
        if(userIdCheck.isEmpty()){
            return null;
        }else{
            loginDto userInfo = userIdCheck.get();
            return userInfo;
        }
    }

    @Override
    public loginDto mangerIdCheck(String id) {
        Optional<loginDto> mangerIdCheck =
                Optional.ofNullable(loginRepository.mangerIdCheck(setInfo(id, null), sql.get("checkMasterId")));
        if(mangerIdCheck.isEmpty()){
            return null;
        }else{
            loginDto mangerInfo = mangerIdCheck.get();
            return mangerInfo;
        }
    }

    @Override
    public loginDto userPwChcek(Integer password) {
        Optional<loginDto> userPwCheck =
                Optional.ofNullable(loginRepository.userPwCheck(setInfo(null, password), sql.get("checkUserPassword")));
        if(userPwCheck.isEmpty()){
            return null;
        }else{
            loginDto userInfo = userPwCheck.get();
            return userInfo;
        }
    }

    @Override
    public loginDto mangerPwCheck(Integer password) {
        Optional<loginDto> mangerPwCheck =
                Optional.ofNullable(loginRepository.mangerPwCheck(setInfo(null,password),sql.get("checkMasterPassword")));
        if(mangerPwCheck.isEmpty()){
            return null;
        }else{
            loginDto mangerInfo = mangerPwCheck.get();
            return mangerInfo;
        }
    }



    private loginDto setInfo(String id, Integer password) {
        loginDto userinfo = new loginDto();
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

}
