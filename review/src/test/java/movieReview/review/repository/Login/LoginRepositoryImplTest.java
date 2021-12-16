package movieReview.review.repository.Login;

import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginRepositoryImplTest {

    @Autowired
    joinRepositoryImpl joinRepository;

    @Autowired
    LoginRepositoryImpl loginRepository;


    loginDto userLogin = new loginDto();
    loginDto mangerLogin = new loginDto();

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
        createuser.setPassword("222");
        createuser.setEmail("abcd");

        joinRepository.createUser(createuser);

        userLogin.setId("jjs1111");
        userLogin.setPassword(222);

        createmanger.setId("imy0111");
        createmanger.setPassword("222");
        createmanger.setEmail("imy0529@asdf");
        createmanger.setNumber(1111);

        joinRepository.createManger(createmanger);

        mangerLogin.setId("imy0111");
        mangerLogin.setPassword(222);
    }

    @AfterEach
    void delete(){
        joinRepository.delete(createuser);
        joinRepository.deleteManger(createmanger);
    }


    @Test
    void userLoginCheck() {
        //when
        loginDto checkuser = loginRepository.userLoginCheck(userLogin,sql.get("userSql"));

        //then
        assertThat(checkuser.getId()).isEqualTo("jjs1111");
        assertThat(checkuser.getPassword()).isEqualTo(222);

    }

    @Test
    void mangerLoginCheck() {
        //when
        loginDto checkmanger = loginRepository.mangerLoginCheck(mangerLogin,sql.get("masterSql"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
    }

    @Test
    void userIdCheck(){
        //when
        loginDto check = loginRepository.userIdCheck(userLogin, sql.get("checkUserId"));

        //then
        assertThat(check.getId()).isEqualTo("jjs1111");
        assertThat(check.getPassword()).isEqualTo(222);
    }

    @Test
    void userPwCheck(){
        //when
        loginDto check = loginRepository.userPwCheck(userLogin, sql.get("checkUserPassword"));
        //then
        assertThat(check.getId()).isEqualTo("jjs1111");
        assertThat(check.getPassword()).isEqualTo(222);

    }

    @Test
    void mangerIdCheck(){
        //when
        loginDto checkmanger = loginRepository.mangerIdCheck(mangerLogin, sql.get("checkMasterId"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
    }

    @Test
    void mangerPwCheck(){
        //when
        loginDto checkmanger = loginRepository.mangerPwCheck(mangerLogin, sql.get("checkMasterPassword"));

        //then
        assertThat(checkmanger.getId()).isEqualTo("imy0111");
        assertThat(checkmanger.getPassword()).isEqualTo(222);
    }
}