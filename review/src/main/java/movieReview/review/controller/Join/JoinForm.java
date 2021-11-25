package movieReview.review.controller.Join;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Component
public class JoinForm {
    @NotBlank(message = "id는 필수로 작성해야 합니다.")
    private String id;

    private Integer number;

    @NotBlank(message = "E-mail은 필수로 작성해야 합니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수로 작성해야 합니다.")
    @Positive(message = "양수로만 작성해 주세요.")
    private Integer password;

    @NotBlank(message = "인증코드는 필수로 작성해주세요.")
    private String joinCode;
}
