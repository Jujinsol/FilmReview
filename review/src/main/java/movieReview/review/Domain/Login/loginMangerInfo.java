package movieReview.review.Domain.Login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class loginMangerInfo {
    @NotBlank(message = "id는 필수로 작성해야 합니다.")
    private String id;

    @NotNull(message = "비밀번호는 필수로 작성해야 합니다.")
    @Positive(message = "양수로만 작성해 주세요.")
    private int password;
}
