package movieReview.review.dto.MovieInfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.nio.file.Path;

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

    private String photoOriName; // 원본 파일명
    private String photoUri; // 파일 저장 경로
    private Path path; // 출력할때 쓸 경로

}
