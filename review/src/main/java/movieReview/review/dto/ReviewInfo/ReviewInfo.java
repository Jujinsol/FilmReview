package movieReview.review.dto.ReviewInfo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ReviewInfo {
    private String photoOriName;

    private int moviePoint;

    private String movieReivew;

    private String reviewUser;
}
