package movieReview.review.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Getter
@Setter
public class userInfo {
    @NotBlank(message = "id는 필수로 작성해야 합니다.")
    private String id;

    @NotBlank(message = "E-mail은 필수로 작성해야 합니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수로 작성해야 합니다.")
    @Positive(message = "양수로만 작성해 주세요.")
    private String password;

    @NotBlank(message = "인증코드는 필수로 작성해주세요.")
    private String joinCode;
}
