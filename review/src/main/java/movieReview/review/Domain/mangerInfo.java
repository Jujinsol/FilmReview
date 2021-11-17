package movieReview.review.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Component
public class mangerInfo{
    @NotBlank(message = "id는 필수로 작성해야 합니다.")
    private String id;

    @NotNull(message = "관리자 번호를 작성해주세요.")
    private Integer number;

    @NotNull(message = "비밀번호는 필수로 작성해야 합니다.")
    @Positive(message = "양수로만 작성해 주세요.")
    private int password;

    @NotBlank(message = "E-mail은 필수로 작성해야 합니다.")
    private String email;
}
