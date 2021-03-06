package movieReview.review.Domain.MovieInfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;

@Getter
@Setter
public class movieInfo {

    @NotBlank
    private String movieName;

    @NotNull
    private MultipartFile moviePoster;

    @NotNull
    private int openYear;

    @NotBlank
    private String directorName;
    @NotBlank
    private String storyLine;
    @NotBlank(message = "예고편 코드를 입력해주세요.")
    private String trailerCode;

    private int moviePoint;
    private String movieReview;

    private String photoOriName; // 원본 파일명
    private String photoUri; // 파일 저장 경로
    private Path path; // 출력할때 쓸 경로

    public static movieInfoBuilder Builder(){
        return new movieInfoBuilder();
    }
}
