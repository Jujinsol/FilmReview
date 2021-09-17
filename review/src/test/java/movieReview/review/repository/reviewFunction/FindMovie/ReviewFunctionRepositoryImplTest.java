package movieReview.review.repository.reviewFunction.FindMovie;

import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.JpaRevieTab;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import movieReview.review.repository.Upload.UploadRepository;
import movieReview.review.repository.Upload.UploadRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewFunctionRepositoryImplTest {
    @Autowired
    ReviewFunctionRepository reviewFunctionRepository;

    @Autowired
    UploadRepository uploadRepository;

    ReviewInfo reviewInfo = new ReviewInfo();

    movieInfo movieinfo = new movieInfo();

    @BeforeEach
    void 리뷰만들기(){
        reviewInfo.setReviewUser("jjsair0412");
        reviewInfo.setMovieReivew("정말재밌어요");
        reviewInfo.setMoviePoint(10);
        reviewInfo.setPhotoOriName("test.png");


        movieinfo.setPhotoOriName("test.png");
        movieinfo.setPhotoUri("static/---");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName("제목");
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");
    }

    @Before
    void 테스트용더미영화정보등록(){
        int insert = uploadRepository.insert(movieinfo);
        assertThat(insert).isEqualTo(1);

        //영화리뷰등록
        int i = reviewFunctionRepository.insertReview(reviewInfo);
        assertThat(i).isEqualTo(1);
    }


    @Test
    void 영화별리뷰전체불러오기() {
        List<JpaRevieTab> jpaRevieTabs = reviewFunctionRepository.selectReview(reviewInfo);
        for (JpaRevieTab review : jpaRevieTabs){
            assertThat(review.getReviewUser()).isEqualTo("jjsair0412");
            assertThat(review.getPhotoOriName()).isEqualTo("test.png");
            assertThat(review.getMoviePoint()).isEqualTo(10);
            assertThat(review.getMovieReivew()).isEqualTo("정말재밌어요");
        }
    }


    @After
    void 테스트용더미정보삭제(){
        // 리뷰삭제
        int i = reviewFunctionRepository.deleteReview(reviewInfo);
        assertThat(i).isEqualTo(1);

        // 영화정보 삭제
        int i2 = uploadRepository.movieDelete(movieinfo);
        assertThat(i2).isEqualTo(1);
    }

}