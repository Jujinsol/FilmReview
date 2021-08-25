package movieReview.review.service.Login.Check;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.Join.joinRepository;
import movieReview.review.repository.Join.joinRepositoryImpl;
import movieReview.review.repository.Login.LoginRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CheckInfoExistImplTest {

    LoginRepositoryImpl loginRepository = new LoginRepositoryImpl();
    CheckInfoExistImpl checkInfoExist = new CheckInfoExistImpl(loginRepository);
    joinRepositoryImpl joinRepository = new joinRepositoryImpl();


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
    void checkUser성공() {
        //when
        Optional<userInfo> jjs1111 = checkInfoExist.checkUser("jjs1111", 222);
        userInfo userInfo = jjs1111.get();
        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(222);
        assertThat(userInfo.getEmail()).isEqualTo("abcd");
    }

    @Test
    void checkUser로그인실패아예다른idpw작성시(){
        //when
        Optional<userInfo> jjj = checkInfoExist.checkUser("jjj", 1);
        //then
        assertThat(jjj).isNull();
    }

    @Test
    void checkManger() {
        //given

        //when

        //then
    }

    @Test
    void userIdCheck() {
        //given

        //when

        //then
    }

    @Test
    void mangerIdCheck() {
        //given

        //when

        //then
    }

    @Test
    void userPwChcek() {
        //given

        //when

        //then
    }

    @Test
    void mangerPwCheck() {
        //given

        //when

        //then
    }
}