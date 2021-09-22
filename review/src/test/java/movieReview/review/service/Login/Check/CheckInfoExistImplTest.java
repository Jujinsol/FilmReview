package movieReview.review.service.Login.Check;

import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import movieReview.review.repository.Login.LoginRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        loginUserInfo userInfo = checkInfoExist.checkUser("jjs1111", 222);

        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(222);
    }

    @Test
    void checkUser로그인실패아예다른idpw작성시(){
        //when
        loginUserInfo jjj = checkInfoExist.checkUser("jjj", 1);
        //then
        assertThat(jjj).isNull();
    }

    @Test
    void checkManger성공() {
        //given
        loginMangerInfo mangerInfo = checkInfoExist.checkManger("imy0111", 222);

        assertThat(mangerInfo.getId()).isEqualTo("imy0111");
        assertThat(mangerInfo.getPassword()).isEqualTo(222);

    }

    @Test
    void checkManger로그인실패아에다른idpw작성시(){
        loginMangerInfo iii = checkInfoExist.checkManger("iii", 11);
        assertThat(iii).isNull();
    }


    @Test
    void userIdCheck성공() {
        //when
        loginUserInfo userInfo = checkInfoExist.userIdCheck("jjs1111");
        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(222);

    }

    @Test
    void userIdCheck실패(){
        //when
        loginUserInfo jjs1111 = checkInfoExist.userIdCheck("jjs0");
        //then
        assertThat(jjs1111).isNull();
    }

    @Test
    void mangerIdCheck성공() {
        //when
        loginMangerInfo mangerInfo = checkInfoExist.mangerIdCheck("imy0111");

        //then
        assertThat(mangerInfo.getId()).isEqualTo("imy0111");
        assertThat(mangerInfo.getPassword()).isEqualTo(222);
    }

    @Test
    void mangerIdCheck실패(){
        //when
        loginMangerInfo imy = checkInfoExist.mangerIdCheck("imy");
        //then
        assertThat(imy).isNull();

    }

    @Test
    void userPwChcek성공() {
        //when
        loginUserInfo userInfo = checkInfoExist.userPwChcek(222);

        //then
        assertThat(userInfo.getId()).isEqualTo("jjs1111");
        assertThat(userInfo.getPassword()).isEqualTo(222);
    }

    @Test
    void userPwCheck실패(){
        //when
        loginUserInfo userInfo = checkInfoExist.userPwChcek(1);
        //then
        assertThat(userInfo).isNull();

    }

    @Test
    void mangerPwCheck성공() {
        //when
        loginMangerInfo mangerInfo = checkInfoExist.mangerPwCheck(222);

        //then
        assertThat(mangerInfo.getId()).isEqualTo("imy0111");
        assertThat(mangerInfo.getPassword()).isEqualTo(222);
    }
}