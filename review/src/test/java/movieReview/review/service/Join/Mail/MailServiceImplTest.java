package movieReview.review.service.Join.Mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.internet.AddressException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MailServiceImplTest {
    @Autowired MailService service;

    @Test
    void 이메일인증코드제대로왔을경우() throws AddressException {
        boolean b = service.checkEmail("jjsair0412@naver.com");
        assertThat(b).isEqualTo(true);
    }

    @Test
    void 이메일인증코드공백포함일경우(){
        String result = service.checkCode("jjs ai r 0412@ naver .com");
        assertThat(result).isEqualTo("jjsair0412@naver.com");
    }

    @Test
    void 이메일인증코드성공적으로온경우(){
        String result = service.checkCode("jjsair0412@naver.com");
        assertThat(result).isEqualTo("jjsair0412@naver.com");
    }

    @Test
    void 이메일인증코드비어있을경우(){
        String result = service.checkCode(" ");
        assertThat(result).isEqualTo("false");
    }

    @Test
    void 이메일인증코드Null일경우(){
        String result = service.checkCode(null);
        assertThat(result).isEqualTo("false");
    }
}