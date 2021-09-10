package movieReview.review.dto.MovieInfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;

@Getter
@Setter
@Entity
@Table(name="photoinfo", schema = "moviereview")
public class JpaMovieInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // id값이 자동으로 증가하기때문에 GenerationType.IDENTITY로 생성
    @Column(name = "photooriname")
    private String photoOriName; // 원본 파일명


    // db에 테이블 속성과 이름이 동일하게 컬럼이름 생성
    // 해당 어노테이션을 사용해서 jpa가 db와 연결함.
    @Column(name = "moviename")
    private String movieName;

    @Column(name = "openyear")
    private int openYear;

    @Column(name = "directorname")
    private String directorName;

    @Column(name = "storyline")
    private String storyLine;

    @Column(name = "photouri")
    private String photoUri; // 파일 저장 경로

}
