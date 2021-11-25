package movieReview.review.Domain.FileInfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
public class photoUriInfo {
    private int id;
    private String photoOriName; // 원본 파일명
    private String photoUri; // 파일 저장 경로
    private Path path; // 출력할때 쓸 경로
}
