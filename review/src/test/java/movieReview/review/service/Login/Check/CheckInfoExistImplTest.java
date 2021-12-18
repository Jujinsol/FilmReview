package movieReview.review.service.Login.Check;

import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import movieReview.review.repository.Login.LoginRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CheckInfoExistImplTest {

    @Autowired
    LoginRepositoryImpl loginRepository;
    @Autowired
    CheckInfoExistImpl checkInfoExist;
    @Autowired
    joinRepositoryImpl joinRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    String encodePassword;
    String password;

    userInfo userinfo = new userInfo();
    mangerInfo mangerinfo = new mangerInfo();

    @BeforeEach
    void createUserAndMaster(){

        password = "222";
        encodePassword = passwordEncoder.encode(password);

        userinfo.setId("jjs1111");
        userinfo.setPassword(encodePassword);
        userinfo.setEmail("abcd");

        joinRepository.createUser(userinfo);


        mangerinfo.setId("imy0111");
        mangerinfo.setPassword(encodePassword);
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
    void checkUser_성공() {
        //when
        loginDto userInfo = checkInfoExist.checkUser("jjs1111", "222");

        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(encodePassword);
    }

    @Test
    void checkUser_로그인실패_아예다른_idpw_작성시(){
        //when
        loginDto jjj = checkInfoExist.checkUser("jjj", "1");
        //then
        assertThat(jjj).isNull();
    }


    @Test
    void checkUser_로그인실패_id_잘못작성(){
        //when
        loginDto jjj = checkInfoExist.checkUser("jjj", "222");
        //then
        assertThat(jjj).isNull();
    }

    @Test
    void checkUser_로그인실패_pw_잘못작성(){
        //when
        loginDto jjj = checkInfoExist.checkUser("jjs1111", "1234");
        //then
        assertThat(jjj).isNull();
    }

    @Test
    void checkManger_성공() {
        //given
        loginDto mangerInfo = checkInfoExist.checkManger("imy0111", "222");

        assertThat(mangerInfo.getId()).isEqualTo("imy0111");
        assertThat(mangerInfo.getPassword()).isEqualTo(encodePassword);
    }

    @Test
    void checkManger_로그인실패_아예다른_idpw_작성시(){
        loginDto iii = checkInfoExist.checkManger("iii", "11");
        assertThat(iii).isNull();
    }


    @Test
    void checkManger_로그인실패_id_잘못작성한경우() {
        //given
        loginDto mangerInfo = checkInfoExist.checkManger("imy0111123", "222");
        assertThat(mangerInfo).isNull();
    }

    @Test
    void checkManger_로그인실패_pw_잘못작성한경우() {
        //given
        loginDto mangerInfo = checkInfoExist.checkManger("imy0111", "212312322");
        assertThat(mangerInfo).isNull();
    }

}