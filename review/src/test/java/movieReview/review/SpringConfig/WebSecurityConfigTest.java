package movieReview.review.SpringConfig;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebSecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void 회원가입시_비밀번호_암호화테스트(){
        String rawPassword = "abcd1234";

        String encodeResult = passwordEncoder.encode(rawPassword);

        assertThat(rawPassword).isNotEqualTo(encodeResult);
        assertThat(passwordEncoder.matches(rawPassword,encodeResult)).isEqualTo(true);
    }


    @Test
    void 회원가입시_비밀번호_암호화테스트_비밀번호숫자만있을경우(){
        String rawPassword = "1234";
        int IRawPassword = 1234;
        System.out.println("rawPassword = " + rawPassword);

        String encodeResult = passwordEncoder.encode(rawPassword);
        System.out.println("encodeResult = " + encodeResult);

        assertThat(rawPassword).isNotEqualTo(encodeResult);
        assertThat(passwordEncoder.matches(Integer.toString(IRawPassword),encodeResult)).isEqualTo(true);
    }


    @Test
    void 비밀번호가_숫자일경우_암호화테스트(){
        int rawPassword = 1234;
        System.out.println("rawPassword = " + rawPassword);

        String encodeResult = passwordEncoder.encode(Integer.toString(rawPassword));
        System.out.println("encodeResult = " + encodeResult);

    }

}