package movieReview.review.Domain.Login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class loginForm {
    @NotBlank(message = "id는 필수로 작성해야 합니다.")
    private String id;

    @NotNull(message = "비밀번호는 필수로 작성해야 합니다.")
    private String password;
}
