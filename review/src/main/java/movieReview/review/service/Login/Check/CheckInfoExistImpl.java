package movieReview.review.service.Login.Check;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.repository.Login.LoginRepository;
import movieReview.review.repository.Login.LoginRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckInfoExistImpl implements CheckInfoExist {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private Map<String, String> sql = new HashMap<>();

    @Autowired
    public CheckInfoExistImpl(LoginRepositoryImpl loginRepository, PasswordEncoder passwordEncoder) {

        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;

        sql.put("userSql", "SELECT userId, userPw,userEmail from user where userId=?");
        sql.put("masterSql", "select masterId, number, pw, email from master where masterId=?");

        sql.put("checkUserId", "SELECT userId, userPw, userEmail from user where userId=?");
        sql.put("checkMasterId", "select masterId, number, pw, email from master where masterId=?");

        sql.put("checkUserPassword", "SELECT userId, userPw, userEmail from user where userPw=?");
        sql.put("checkMasterPassword", "select masterId, number, pw, email from master where pw=?");

    }


    @Override
    public loginDto checkUser(String id, String password) {
        Optional<loginDto> userInfoCheck =
                Optional.ofNullable((loginRepository.userLoginCheck(setInfo(id, password), sql.get("userSql"))));
        loginDto userInfo = userInfoCheck.get();
        boolean matches = passwordEncoder.matches(password, userInfo.getPassword());
        if (matches) {
            return userInfo;
        }
        return null;

    }

    @Override
    public loginDto checkManger(String id, String password) {

        Optional<loginDto> mangerInfoCheck =
                Optional.ofNullable(loginRepository.mangerLoginCheck(setInfo(id, password), sql.get("masterSql")));
        loginDto mangerInfo = mangerInfoCheck.get();
        boolean matches = passwordEncoder.matches(password, mangerInfo.getPassword());
        if (matches) {
            return mangerInfo;
        }
        return null;

    }

    @Override
    public loginDto userIdCheck(String id) {
        Optional<loginDto> userIdCheck =
                Optional.ofNullable(loginRepository.userIdCheck(setInfo(id, null), sql.get("checkUserId")));
        if (userIdCheck.isEmpty()) {
            return null;
        } else {
            loginDto userInfo = userIdCheck.get();
            return userInfo;
        }
    }

    @Override
    public loginDto mangerIdCheck(String id) {
        Optional<loginDto> mangerIdCheck =
                Optional.ofNullable(loginRepository.mangerIdCheck(setInfo(id, null), sql.get("checkMasterId")));
        if (mangerIdCheck.isEmpty()) {
            return null;
        } else {
            loginDto mangerInfo = mangerIdCheck.get();
            return mangerInfo;
        }
    }

    @Override
    public loginDto userPwChcek(String password) {
        Optional<loginDto> userPwCheck =
                Optional.ofNullable(loginRepository.userPwCheck(setInfo(null, password), sql.get("checkUserPassword")));

        loginDto userInfo = userPwCheck.get();
        boolean matches = passwordEncoder.matches(password, userInfo.getPassword());
        if (matches) {
            return userInfo;
        }
        return null;
    }

    @Override
    public loginDto mangerPwCheck(String password) {
        Optional<loginDto> mangerPwCheck =
                Optional.ofNullable(loginRepository.mangerPwCheck(setInfo(null, password), sql.get("checkMasterPassword")));
        loginDto mangerInfo = mangerPwCheck.get();
        boolean matches = passwordEncoder.matches(password, mangerInfo.getPassword());
        if (matches) {
            return mangerInfo;
        }
        return null;
    }


    private loginDto setInfo(String id, String password) {
        loginDto userinfo = new loginDto();
        if (id == null) {
            userinfo.setPassword(password);
            return userinfo;
        } else if (password == null) {
            userinfo.setId(id);
            return userinfo;
        } else {
            userinfo.setId(id);
            userinfo.setPassword(password);
            return userinfo;
        }
    }

}
