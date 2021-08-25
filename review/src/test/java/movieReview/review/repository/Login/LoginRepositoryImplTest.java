package movieReview.review.repository.Login;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginRepositoryImplTest {
    joinRepositoryImpl joinRepository = new joinRepositoryImpl();
    LoginRepositoryImpl loginRepository = new LoginRepositoryImpl();


    userInfo userinfo = new userInfo();
    mangerInfo mangerinfo = new mangerInfo();
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

        userinfo.setId("jjs1111");
        userinfo.setPassword(222);
        userinfo.setEmail("abcd");

        joinRepository.createUser(userinfo);


        mangerinfo.setId("imy0111");
        mangerinfo.setPassword(222);
        mangerinfo.setEmail("imy0529@asdf");
        mangerinfo.setNumber(1111);

        joinRepository.createManger(mangerinfo);
    }

    @AfterEach
    void delete(){
        joinRepository.delete(userinfo);
        joinRepository.deleteManger(mangerinfo);
    }


    @Test
    void userLoginCheck() {
        //when
        userInfo checkuser = loginRepository.userLoginCheck(userinfo,sql.get("userSql"));

        //then
        assertThat(checkuser.getId()).isEqualTo("jjs1111");
        assertThat(checkuser.getEmail()).isEqualTo("abcd");
        assertThat(checkuser.getPassword()).isEqualTo(222);

    }

    @Test
    void mangerLoginCheck() {
        //when
        mangerInfo checkmanger = loginRepository.mangerLoginCheck(mangerinfo,sql.get("masterSql"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
        assertThat(checkmanger.getNumber()).isEqualTo(1111);
        assertThat(checkmanger.getEmail()).isEqualTo("imy0529@asdf");

    }

    @Test
    void userIdCheck(){
        //when
        userInfo check = loginRepository.userIdCheck(userinfo, sql.get("checkUserId"));

        //then
        assertThat(check.getId()).isEqualTo("jjs1111");
        assertThat(check.getEmail()).isEqualTo("abcd");
        assertThat(check.getPassword()).isEqualTo(222);
    }

    @Test
    void userPwCheck(){
        //when
        userInfo check = loginRepository.userPwCheck(userinfo, sql.get("checkUserPassword"));
        //then
        assertThat(check.getId()).isEqualTo("jjs1111");
        assertThat(check.getEmail()).isEqualTo("abcd");
        assertThat(check.getPassword()).isEqualTo(222);

    }

    @Test
    void mangerIdCheck(){
        //when
        mangerInfo checkmanger = loginRepository.mangerIdCheck(mangerinfo, sql.get("checkMasterId"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
        assertThat(checkmanger.getNumber()).isEqualTo(1111);
        assertThat(checkmanger.getEmail()).isEqualTo("imy0529@asdf");
    }

    @Test
    void mangerPwCheck(){
        //when
        mangerInfo checkmanger = loginRepository.mangerPwCheck(mangerinfo, sql.get("checkMasterPassword"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
        assertThat(checkmanger.getNumber()).isEqualTo(1111);
        assertThat(checkmanger.getEmail()).isEqualTo("imy0529@asdf");
    }
}