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
import java.util.UUID;

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

    String userid = "user1";
    String userpassword = UUID.randomUUID().toString();
    String userEmail = UUID.randomUUID().toString();

    String managerId = "manager1";
    String managerPassword = UUID.randomUUID().toString();
    String managerEmail = UUID.randomUUID().toString();


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

        createuser.setId(userid);
        createuser.setPassword(userpassword);
        createuser.setEmail(userEmail);

        joinRepository.createUser(createuser);

        userLogin.setId(userid);
        userLogin.setPassword(userpassword);

        createmanger.setId(managerId);
        createmanger.setPassword(managerPassword);
        createmanger.setEmail(managerEmail);
        createmanger.setNumber(1111);

        joinRepository.createManger(createmanger);

        mangerLogin.setId(managerId);
        mangerLogin.setPassword(managerPassword);
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
        assertThat(checkuser.getId()).isEqualTo(userid);
        assertThat(checkuser.getPassword()).isEqualTo(userpassword);

    }

    @Test
    void mangerLoginCheck() {
        //when
        loginDto checkmanger = loginRepository.mangerLoginCheck(mangerLogin,sql.get("masterSql"));

        //then
        assertThat(checkmanger.getId()).isEqualTo(managerId);
        assertThat(checkmanger.getPassword()).isEqualTo(managerPassword);
    }

    @Test
    void userIdCheck(){
        //when
        loginDto check = loginRepository.userIdCheck(userLogin, sql.get("checkUserId"));

        //then
        assertThat(check.getId()).isEqualTo(userid);
        assertThat(check.getPassword()).isEqualTo(userpassword);
    }

    @Test
    void userPwCheck(){
        //when
        loginDto check = loginRepository.userPwCheck(userLogin, sql.get("checkUserPassword"));
        //then
        assertThat(check.getId()).isEqualTo(userid);
        assertThat(check.getPassword()).isEqualTo(userpassword);

    }

    @Test
    void mangerIdCheck(){
        //when
        loginDto checkmanger = loginRepository.mangerIdCheck(mangerLogin, sql.get("checkMasterId"));

        //then
        assertThat(checkmanger.getId()).isEqualTo(managerId);
        assertThat(checkmanger.getPassword()).isEqualTo(managerPassword);
    }

    @Test
    void mangerPwCheck(){
        //when
        loginDto checkmanger = loginRepository.mangerPwCheck(mangerLogin, sql.get("checkMasterPassword"));

        //then
        assertThat(checkmanger.getId()).isEqualTo(managerId);
        assertThat(checkmanger.getPassword()).isEqualTo(managerPassword);
    }
}