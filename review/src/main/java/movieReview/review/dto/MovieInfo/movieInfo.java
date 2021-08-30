package movieReview.review.dto.MovieInfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class movieInfo {
    private String movieName;
    private MultipartFile moviePoster;
    private int openYear;
    private String directorName;
    private String storyLine;
}
