package movieReview.review.Domain.MovieInfo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="photoInfo", schema = "moviereview")
public class JpaMovieInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // id값이 자동으로 증가하기때문에 GenerationType.IDENTITY로 생성
    @Column(name = "photoOriName")
    private String photoOriName; // 원본 파일명


    // db에 테이블 속성과 이름이 동일하게 컬럼이름 생성
    // 해당 어노테이션을 사용해서 jpa가 db와 연결함.
    @Column(name = "movieName")
    private String movieName;

    @Column(name = "openYear")
    private int openYear;

    @Column(name = "directorName")
    private String directorName;

    @Column(name = "storyline")
    private String storyLine;

    @Column(name = "photoUri")
    private String photoUri; // 파일 저장 경로

    @Column(name = "trailerCode")
    private String trailerCode;

}
