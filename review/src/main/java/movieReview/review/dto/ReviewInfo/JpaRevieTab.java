package movieReview.review.dto.ReviewInfo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="reviewTab", schema = "moviereview")
public class JpaRevieTab {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // id값이 자동으로 증가하기때문에 GenerationType.IDENTITY로 생성
    @Column(name ="photoOriName")
    private String photoOriName;

    @Column(name = "moviePoint")
    private int moviePoint;

    @Column(name ="movieReview")
    private String movieReivew;

    @Column(name ="reviewUser")
    private String reviewUser;

}
