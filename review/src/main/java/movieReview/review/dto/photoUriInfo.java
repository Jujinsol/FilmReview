package movieReview.review.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class photoUriInfo {
    private int id;
    private String photoOriName; // 원본 파일명
    private String photoUri; // 파일 저장 경로
}
