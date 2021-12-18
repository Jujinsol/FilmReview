package movieReview.review.service.Login;

import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginServiceImplTest {


    @Autowired
    LoginServiceImpl loginServiceImpl;

    @Autowired
    joinRepositoryImpl joinRepository;

    userInfo userinfo = new userInfo();
    mangerInfo mangerinfo = new mangerInfo();

    loginUserInfo loginuser = new loginUserInfo();
    loginMangerInfo loginmanger = new loginMangerInfo();

    @BeforeEach
    void createUserAndMaster(){

        userinfo.setId("jjs1111");
        userinfo.setPassword("222");
        userinfo.setEmail("abcd");

        joinRepository.createUser(userinfo);

        loginuser.setId("jjs1111");
        loginuser.setPassword("222");

        mangerinfo.setId("imy0111");
        mangerinfo.setPassword("222");
        mangerinfo.setEmail("imy0529@asdf");
        mangerinfo.setNumber(1111);

        joinRepository.createManger(mangerinfo);

        loginmanger.setId("imy0111");
        loginmanger.setPassword("222");
    }

    @AfterEach
    void delete(){
        joinRepository.delete(userinfo);
        joinRepository.deleteManger(mangerinfo);
    }

    @Test
    void findUserOrManger(){
        //사용자일경우
        int userResult = loginServiceImpl.FirstCheck("jjs1111");
        assertThat(userResult).isEqualTo(1);

        //관리자일경우
        int mangerResult = loginServiceImpl.FirstCheck("imy0111");
        assertThat(mangerResult).isEqualTo(2);

        //아예존재하지 않을 경우
        int failure = loginServiceImpl.FirstCheck("a");
        assertThat(failure).isEqualTo(0);
    }

    @Test
    void userLogin() {
        //사용자 성공
        loginUserInfo result = loginServiceImpl.userLogin(loginuser);

        assertThat(result.getId()).isEqualTo("jjs1111");
        assertThat(result.getPassword()).isEqualTo("222");
    }

    @Test
    void userLoginid실패시(){
        loginUserInfo user = new loginUserInfo();
        user.setId("jj");
        user.setPassword("222");

        loginUserInfo result = loginServiceImpl.userLogin(user);

        assertThat(result).isEqualTo(null);
    }

    @Test
    void mangerLogin() {
        // 관리자 성공
        loginMangerInfo result = loginServiceImpl.mangerLogin(loginmanger);

        assertThat(result.getId()).isEqualTo("imy0111");
        assertThat(result.getPassword()).isEqualTo("222");

    }
}