package movieReview.review.repository;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class joinRepositoryImplTest {
    joinRepositoryImpl jo = new joinRepositoryImpl();
    userInfo userinfo = new userInfo();
    mangerInfo mangerinfo = new mangerInfo();

    @BeforeEach
    void createUser() {
        //given
        userinfo.setId("jjs0412");
        userinfo.setPassword(1234);
        userinfo.setEmail("jjsair0412@naver.com");

        //when
        int result = jo.createUser(userinfo);
        //then
        assertThat(result).isEqualTo(1);

    }


    @AfterEach
    void delete() {
        //when
        int delete = jo.delete(userinfo);
        //then
        assertThat(delete).isEqualTo(1);
    }

    @Test
    void createAndDeleteManger() {
        //given
        joinRepositoryImpl jo = new joinRepositoryImpl();
        mangerinfo.setId("imy0529");
        mangerinfo.setPassword(1234);
        mangerinfo.setEmail("imy0529@naver.com");
        mangerinfo.setNumber(3333);
        //when
        int createResult = jo.createManger(mangerinfo);
        //then
        assertThat(createResult).isEqualTo(1);

        //삭제
        int deleteResult = jo.deleteManger(mangerinfo);

        assertThat(deleteResult).isEqualTo(1);
    }


    @Test
    void selectMyinfo() {
        //given
        userInfo userInfo = jo.selectMyinfo(userinfo);
        //when
        String id = userInfo.getId();
        int password = userInfo.getPassword();
        String email = userInfo.getEmail();
        //then
        assertThat(id).isEqualTo("jjs0412");
        assertThat(password).isEqualTo(1234);
        assertThat(email).isEqualTo("jjsair0412@naver.com");
    }

    @Test
    void updateMyinfo() {
        //given
        userInfo updateParam = new userInfo();
        //when
        updateParam.setId("jjs0412");
        updateParam.setPassword(3333);
        int i = jo.updateMyinfo(updateParam);
        //then

    }

    @Test
    void mangerInfoSelect(){
        //given
        mangerinfo.setId("imy0529");
        mangerinfo.setEmail("imy0529@naver.com");
        mangerinfo.setPassword(1111);
        mangerinfo.setNumber(2222);

        jo.createManger(mangerinfo);

        //when
        mangerInfo findResult = jo.selectMangerinfo(mangerinfo);
        //then
        assertThat(findResult.getId()).isEqualTo("imy0529");
        assertThat(findResult.getEmail()).isEqualTo("imy0529@naver.com");
        assertThat(findResult.getPassword()).isEqualTo(1111);
        assertThat(findResult.getNumber()).isEqualTo(2222);

        jo.deleteManger(mangerinfo);
    }
}