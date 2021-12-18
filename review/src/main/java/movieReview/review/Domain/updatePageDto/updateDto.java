package movieReview.review.Domain.updatePageDto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class updateDto {
    private String id;


    @NotNull(message = "필수로 작성해야 합니다.")
    private String password;
}
