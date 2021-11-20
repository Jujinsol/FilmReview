package movieReview.review.service.Join;

import movieReview.review.controller.Join.JoinForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class joinServiceImplTest {

    @Autowired joinService joinService;

    @Test
    @Transactional
    @Rollback
    void 유저회원가입성공(){

        JoinForm joinForm = new JoinForm();
        joinForm.setId("MyUser");
        joinForm.setEmail("jjsair0412@naver.com");
        joinForm.setPassword(1234);

        String result = joinService.join(joinForm);

        assertThat(result).isEqualTo("사용자");
    }

    @Test
    @Transactional
    @Rollback
    void 유저회원가입실패_ID_중복(){
        JoinForm joinForm = new JoinForm();
        joinForm.setId("MyUser");
        joinForm.setEmail("jjsair0412@naver.com");
        joinForm.setPassword(1234);

        JoinForm joinForm2 = new JoinForm();
        joinForm2.setId("MyUser");
        joinForm2.setEmail("jjsair04122@naver.com");
        joinForm2.setPassword(12345);

        joinService.join(joinForm);
        String result2 = joinService.join(joinForm2);

        assertThat(result2).isEqualTo("JoinForm.id");
    }

    @Test
    @Transactional
    @Rollback
    void 관리자가입성공(){
        JoinForm joinForm = new JoinForm();
        joinForm.setId("MyMang");
        joinForm.setEmail("jjsair0412@naver.com");
        joinForm.setPassword(1234);
        joinForm.setNumber(1111);

        String result = joinService.join(joinForm);
        assertThat(result).isEqualTo("관리자");
    }

    @Test
    @Transactional
    @Rollback
    void 관리자가입실패_ID_중복(){
        JoinForm joinForm = new JoinForm();
        joinForm.setId("MyMang");
        joinForm.setEmail("jjsair0412@naver.com");
        joinForm.setPassword(1234);
        joinForm.setNumber(1111);

        JoinForm joinForm2 = new JoinForm();
        joinForm2.setId("MyMang");
        joinForm2.setEmail("jjsair04122@naver.com");
        joinForm2.setPassword(12345);
        joinForm2.setNumber(11111);

        joinService.join(joinForm);
        String result = joinService.join(joinForm2);

        assertThat(result).isEqualTo("JoinForm.id");
    }
}