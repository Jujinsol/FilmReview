package movieReview.review.service;

import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import movieReview.review.service.Join.joinServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class joinServiceImplTest {
    joinRepositoryImpl jo = new joinRepositoryImpl();
    userInfo userInfo = new userInfo();
    mangerInfo mangerInfo = new mangerInfo();
    joinServiceImpl joService = new joinServiceImpl(jo,userInfo,mangerInfo);


    @Test
    void myInfo() {

        //given
        int userJoinResult = joService.join("jjsair0412@naver.com", "jjsair0412", 1234,null);
        assertThat(userJoinResult).isEqualTo(1);

        var returnInfo = joService.myInfo("jjsair0412");

        //when
        int returnPassword = returnInfo.getPassword();
        String returnId = returnInfo.getId();
        String returnEmail = returnInfo.getEmail();

        //then
        assertThat(returnPassword).isEqualTo(1234);
        assertThat(returnId).isEqualTo("jjsair0412");
        assertThat(returnEmail).isEqualTo("jjsair0412@naver.com");

        int deleteResult = joService.delete("jjsair0412", 1234);
        assertThat(deleteResult).isEqualTo(1);
    }

    @Test
    void mangerJoin() {
        int mangerJoinResult = joService.join("jjsair0412@naver.com", "jjsair0412", 1234, 9999);
        assertThat(mangerJoinResult).isEqualTo(1);

        mangerInfo.setId("jjsair0412");
        int result = jo.deleteManger(mangerInfo);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void update() {
    }
}