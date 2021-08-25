package movieReview.review.service.Login;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import org.assertj.core.api.Assertions;
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
        userInfo result = loginServiceImpl.userLogin(userinfo);

        assertThat(result.getId()).isEqualTo("jjs1111");
        assertThat(result.getPassword()).isEqualTo(222);
        assertThat(result.getEmail()).isEqualTo("abcd");
    }

    @Test
    void userLoginid실패시(){
        userInfo user = new userInfo();
        user.setId("jj");
        user.setPassword(222);
        user.setEmail("abcd");
        userInfo result = loginServiceImpl.userLogin(user);

        assertThat(result).isEqualTo("id문제");
    }

    @Test
    void mangerLogin() {
        // 관리자 성공
        mangerInfo result = loginServiceImpl.mangerLogin(mangerinfo);

        assertThat(result.getId()).isEqualTo("imy0111");
        assertThat(result.getEmail()).isEqualTo("imy0529@asdf");
        assertThat(result.getPassword()).isEqualTo(222);
        assertThat(result.getNumber()).isEqualTo(1111);

    }
}