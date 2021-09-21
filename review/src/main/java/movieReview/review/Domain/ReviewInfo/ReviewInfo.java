package movieReview.review.Domain.ReviewInfo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
public class ReviewInfo {
    private String photoOriName;

    @NotNull(message = "평점을 작성해 주세요..")
    @Positive(message = "양수로만 작성해 주세요.")
    private int moviePoint;

    @NotBlank(message = "리뷰를 작성해 주세요..")
    private String movieReivew;

    private String reviewUser;
}
