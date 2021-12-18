package movieReview.review.service.Login;

import movieReview.review.Domain.Login.loginForm;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.controller.Join.JoinForm;
import movieReview.review.repository.Join.joinRepository;
import movieReview.review.service.Join.joinService;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginServiceImplTest {


    @Autowired
    LoginServiceImpl loginServiceImpl;

    @Autowired
    joinService joinService;

    @Autowired
    joinRepository joinRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    JoinForm userinfo = new JoinForm();
    JoinForm mangerinfo = new JoinForm();


    @BeforeEach
    void 유저_관리자_회원가입(){

        userinfo.setId("jjs1111");
        userinfo.setPassword("222");
        userinfo.setEmail("abcd");

        joinService.join(userinfo);

        mangerinfo.setId("imy0111");
        mangerinfo.setPassword("222");
        mangerinfo.setEmail("imy0529@asdf");
        mangerinfo.setNumber(1111);

        joinService.join(mangerinfo);
    }

    @AfterEach
    void delete(){
        userInfo delUser = new userInfo();
        mangerInfo delManager = new mangerInfo();

        delUser.setId(userinfo.getId());
        delManager.setId(mangerinfo.getId());

        joinRepository.delete(delUser);
        joinRepository.deleteManger(delManager);
    }

    // 회원가입
    // matches
    @Test
    void 사용자_로그인_성공(){
        loginForm form = new loginForm();
        form.setId("jjs1111");
        form.setPassword("222");
        int i = loginServiceImpl.loginResult(form);
        assertThat(i).isEqualTo(1);
    }

    @Test
    void 사용자_로그인_실패_PW_불일치(){
        loginForm form = new loginForm();
        form.setId("jjs1111");
        form.setPassword("1");
        int i = loginServiceImpl.loginResult(form);
        assertThat(i).isEqualTo(0);
    }

    @Test
    void 관리자_로그인_성공(){
        loginForm form = new loginForm();
        form.setId("imy0111");
        form.setPassword("222");
        int i = loginServiceImpl.loginResult(form);
        assertThat(i).isEqualTo(1);
    }

    @Test
    void 관리자_로그인_실패_PW_불일치(){
        loginForm form = new loginForm();
        form.setId("imy0111");
        form.setPassword("1");
        int i = loginServiceImpl.loginResult(form);
        assertThat(i).isEqualTo(0);
    }

    @Test
    void 정보_없어서_회원가입_필요(){
        loginForm form = new loginForm();
        form.setId("A");
        form.setPassword("222");
        int i = loginServiceImpl.loginResult(form);
        assertThat(i).isEqualTo(2);
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

}