package movieReview.review.repository.Login;

import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LoginRepositoryImplTest {
    joinRepositoryImpl joinRepository = new joinRepositoryImpl();
    LoginRepositoryImpl loginRepository = new LoginRepositoryImpl();


    loginUserInfo userinfo = new loginUserInfo();
    loginMangerInfo mangerinfo = new loginMangerInfo();

    loginUserInfo loginuser = new loginUserInfo();
    loginMangerInfo loginmanger = new loginMangerInfo();

    mangerInfo createmanger = new mangerInfo();
    userInfo createuser = new userInfo();
    public static Map<String,String> sql = new HashMap<>();

    @BeforeAll
    static void createSql(){
        sql.put("userSql","SELECT userId, userPw,userEmail from user where userId=? AND userPw =?;");
        sql.put("masterSql","select masterId, number, pw, email from master where masterId=? And pw=?");

        sql.put("checkUserId","SELECT userId, userPw, userEmail from user where userId=?");
        sql.put("checkMasterId","select masterId, number, pw, email from master where masterId=?");

        sql.put("checkUserPassword","SELECT userId, userPw, userEmail from user where userPw=?");
        sql.put("checkMasterPassword","select masterId, number, pw, email from master where pw=?");
    }

    @BeforeEach
    void createUserAndMaster(){

        createuser.setId("jjs1111");
        createuser.setPassword(222);
        createuser.setEmail("abcd");

        joinRepository.createUser(createuser);

        loginuser.setId("jjs1111");
        loginuser.setPassword(222);

        createmanger.setId("imy0111");
        createmanger.setPassword(222);
        createmanger.setEmail("imy0529@asdf");
        createmanger.setNumber(1111);

        joinRepository.createManger(createmanger);

        loginmanger.setId("imy0111");
        loginmanger.setPassword(222);
    }

    @AfterEach
    void delete(){
        joinRepository.delete(createuser);
        joinRepository.deleteManger(createmanger);
    }


    @Test
    void userLoginCheck() {
        //when
        loginUserInfo checkuser = loginRepository.userLoginCheck(loginuser,sql.get("userSql"));

        //then
        assertThat(checkuser.getId()).isEqualTo("jjs1111");
        assertThat(checkuser.getPassword()).isEqualTo(222);

    }

    @Test
    void mangerLoginCheck() {
        //when
        loginMangerInfo checkmanger = loginRepository.mangerLoginCheck(loginmanger,sql.get("masterSql"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
    }

    @Test
    void userIdCheck(){
        //when
        loginUserInfo check = loginRepository.userIdCheck(loginuser, sql.get("checkUserId"));

        //then
        assertThat(check.getId()).isEqualTo("jjs1111");
        assertThat(check.getPassword()).isEqualTo(222);
    }

    @Test
    void userPwCheck(){
        //when
        loginUserInfo check = loginRepository.userPwCheck(loginuser, sql.get("checkUserPassword"));
        //then
        assertThat(check.getId()).isEqualTo("jjs1111");
        assertThat(check.getPassword()).isEqualTo(222);

    }

    @Test
    void mangerIdCheck(){
        //when
        loginMangerInfo checkmanger = loginRepository.mangerIdCheck(loginmanger, sql.get("checkMasterId"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
    }

    @Test
    void mangerPwCheck(){
        //when
        loginMangerInfo checkmanger = loginRepository.mangerPwCheck(loginmanger, sql.get("checkMasterPassword"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
    }
}