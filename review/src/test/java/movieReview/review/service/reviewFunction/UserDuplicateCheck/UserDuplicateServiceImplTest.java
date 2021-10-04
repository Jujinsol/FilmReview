package movieReview.review.service.reviewFunction.UserDuplicateCheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDuplicateServiceImplTest {

    UserDuplicateCheckService service = new UserDuplicateServiceImpl();
    @Test
    void 중복등록한경우() throws JsonProcessingException {
        String id = "asdf";
        String ids = "[\"asdf\", \"bbb\", \"bbb\", \"bbb\"]";

        assertThat(service.DuplicateCheck(id, ids)).isEqualTo(true);
    }

    @Test
    void 중복등록이아닌경우() throws JsonProcessingException{
        String id = "fff";
        String ids = "[\"asdf\", \"bbb\", \"bbb\", \"bbb\"]";

        assertThat(service.DuplicateCheck(id,ids)).isEqualTo(false);
    }
}