package movieReview.review.repository.reviewFunction.FindMovie;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.Upload.UploadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ReviewFunctionRepositoryImplTest {
    @Autowired
    ReviewFunctionRepository reviewFunctionRepository;

    @Autowired
    UploadRepository uploadRepository;

    ReviewInfo reviewInfo = new ReviewInfo();
    ReviewInfo reviewInfo2 = new ReviewInfo();

    ReviewInfo findReview = new ReviewInfo();


    @BeforeEach
    void 리뷰만들기() {
        reviewInfo.setReviewUser("jjsair0412");
        reviewInfo.setMovieReivew("정말재밌어요");
        reviewInfo.setMoviePoint(10);
        reviewInfo.setPhotoOriName("test.png");

        reviewInfo2.setReviewUser("imy123");
        reviewInfo2.setMovieReivew("정말재밌어요증말");
        reviewInfo2.setMoviePoint(12);
        reviewInfo2.setPhotoOriName("test.png");

        findReview.setPhotoOriName("test.png");


        int i = reviewFunctionRepository.insertReview(reviewInfo);
        int i2 = reviewFunctionRepository.insertReview(reviewInfo2);
        assertThat(i).isEqualTo(1);
        assertThat(i2).isEqualTo(1);
    }

    @Test
    void 영화별리뷰전체불러오기() throws ClassNotFoundException {

        List<JpaRevieTab> reviews = reviewFunctionRepository.selectReview(findReview);
        JpaRevieTab jpaReviewOne = reviews.get(0);
        Assertions.assertAll(
                () -> assertThat(jpaReviewOne.getReviewUser()).isEqualTo("jjsair0412"),
                () -> assertThat(jpaReviewOne.getMovieReivew()).isEqualTo("정말재밌어요"),
                () -> assertThat(jpaReviewOne.getMoviePoint()).isEqualTo(10),
                () -> assertThat(jpaReviewOne.getPhotoOriName()).isEqualTo("test.png")
        );

        JpaRevieTab jpaReviewTwo = reviews.get(1);
        Assertions.assertAll(
                () -> assertThat(jpaReviewTwo.getReviewUser()).isEqualTo("imy123"),
                () -> assertThat(jpaReviewTwo.getMovieReivew()).isEqualTo("정말재밌어요증말"),
                () -> assertThat(jpaReviewTwo.getMoviePoint()).isEqualTo(12),
                () -> assertThat(jpaReviewTwo.getPhotoOriName()).isEqualTo("test.png")
        );
    }


    @AfterEach
    void 테스트용더미정보삭제() {
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setReviewUser("imy123");
        reviewInfo.setPhotoOriName("test.png");

        ReviewInfo reviewInfo1 = new ReviewInfo();
        reviewInfo1.setReviewUser("jjsair0412");
        reviewInfo1.setPhotoOriName("test.png");
        // 리뷰삭제
        int i = reviewFunctionRepository.deleteReview(reviewInfo);
        assertThat(i).isEqualTo(1);


        int i2 = reviewFunctionRepository.deleteReview(reviewInfo1);
        assertThat(i2).isEqualTo(1);
    }

}