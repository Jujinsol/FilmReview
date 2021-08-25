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
        userInfo userInfo = checkInfoExist.checkUser("jjs1111", 222);

        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(222);
        assertThat(userInfo.getEmail()).isEqualTo("abcd");
    }

    @Test
    void checkUser로그인실패아예다른idpw작성시(){
        //when
        userInfo jjj = checkInfoExist.checkUser("jjj", 1);
        //then
        assertThat(jjj).isNull();
    }

    @Test
    void checkManger성공() {
        //given
        mangerInfo mangerInfo = checkInfoExist.checkManger("imy0111", 222);

        assertThat(mangerInfo.getId()).isEqualTo("imy0111");
        assertThat(mangerInfo.getEmail()).isEqualTo("imy0529@asdf");
        assertThat(mangerInfo.getNumber()).isEqualTo(1111);
        assertThat(mangerInfo.getPassword()).isEqualTo(222);

    }

    @Test
    void checkManger로그인실패아에다른idpw작성시(){
        mangerInfo iii = checkInfoExist.checkManger("iii", 11);
        assertThat(iii).isNull();
    }


    @Test
    void userIdCheck성공() {
        //when
        userInfo userInfo = checkInfoExist.userIdCheck("jjs1111");
        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(222);
        assertThat(userInfo.getEmail()).isEqualTo("abcd");

    }

    @Test
    void userIdCheck실패(){
        //when
        userInfo jjs1111 = checkInfoExist.userIdCheck("jjs0");
        //then
        assertThat(jjs1111).isNull();
    }

    @Test
    void mangerIdCheck성공() {
        //when
        mangerInfo mangerInfo = checkInfoExist.mangerIdCheck("imy0111");

        //then
        assertThat(mangerInfo.getId()).isEqualTo("imy0111");
        assertThat(mangerInfo.getEmail()).isEqualTo("imy0529@asdf");
        assertThat(mangerInfo.getNumber()).isEqualTo(1111);
        assertThat(mangerInfo.getPassword()).isEqualTo(222);
    }

    @Test
    void mangerIdCheck실패(){
        //when
        mangerInfo imy = checkInfoExist.mangerIdCheck("imy");
        //then
        assertThat(imy).isNull();

    }

    @Test
    void userPwChcek성공() {
        //when
        userInfo userInfo = checkInfoExist.userPwChcek(222);

        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(222);
        assertThat(userInfo.getEmail()).isEqualTo("abcd");
    }

    @Test
    void userPwCheck실패(){
        //when
        userInfo userInfo = checkInfoExist.userPwChcek(1);
        //then
        assertThat(userInfo).isNull();

    }

    @Test
    void mangerPwCheck성공() {
        //when
        mangerInfo mangerInfo = checkInfoExist.mangerPwCheck(222);

        //then
        assertThat(mangerInfo.getId()).isEqualTo("imy0111");
        assertThat(mangerInfo.getEmail()).isEqualTo("imy0529@asdf");
        assertThat(mangerInfo.getNumber()).isEqualTo(1111);
        assertThat(mangerInfo.getPassword()).isEqualTo(222);
    }
}