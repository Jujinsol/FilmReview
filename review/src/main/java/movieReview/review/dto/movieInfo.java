package movieReview.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class movieInfo {
    private String movieName;
    private String moviePoster;
    private int openYear;
    private String directorName;
    private String storyLine;
}
