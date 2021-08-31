package movieReview.review.dto.MovieInfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class movieInfo {
    @NotBlank
    private String movieName;

    private MultipartFile moviePoster;

    @NotNull
    private int openYear;

    @NotBlank
    private String directorName;
    @NotBlank
    private String storyLine;
}
