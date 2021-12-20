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

    }

    private loginDto matcheTest(CharSequence rawPassword, String encodePassword, loginDto dto){
        boolean matches = passwordEncoder.matches(rawPassword, encodePassword);
        if (matches) {
            return dto;
        }
        return null;
    }

    @Override
    public loginDto checkUser(String id, String password) {
        loginDto userInfoCheck = loginRepository.userLoginCheck(setInfo(id, password), sql.get("userSql"));
        return userInfoCheck == null ? null : matcheTest(password,userInfoCheck.getPassword(), userInfoCheck);
    }

    @Override
    public loginDto checkManger(String id, String password) {
        loginDto mangerInfoCheck = loginRepository.mangerLoginCheck(setInfo(id, password), sql.get("masterSql"));
        return mangerInfoCheck == null ? null : matcheTest(password,mangerInfoCheck.getPassword(), mangerInfoCheck);
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
