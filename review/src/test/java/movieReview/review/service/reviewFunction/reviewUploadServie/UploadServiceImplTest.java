package movieReview.review.service.reviewFunction.reviewUploadServie;

import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import movieReview.review.repository.Upload.UploadRepository;
import movieReview.review.service.Upload.UploadService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UploadServiceImplTest {
    @Autowired
    ReviewUploadService reviewUpload;
    @Autowired
    UploadService uploadService;
    @Autowired
    UploadRepository uploadRepository;

    ReviewInfo reviewInfo = new ReviewInfo();
    movieInfo movieinfo = new movieInfo();

    @BeforeEach
    void createMovieInfo(){

        movieinfo.setPhotoOriName("test.png");
        movieinfo.setPhotoUri("static/---");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName("제목");
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");

        uploadService.create(movieinfo);
    }

    @Test
    void reviewUpload() {
        //given
        reviewInfo.setPhotoOriName("test.png");
        reviewInfo.setReviewUser("jjsair0412");
        reviewInfo.setMoviePoint(1);
        reviewInfo.setMovieReivew("hello");
        //when
        int i = reviewUpload.reviewUpload(reviewInfo);
        //then
        assertThat(i).isEqualTo(1);

        int i1 = reviewUpload.deleteReview(reviewInfo);
        assertThat(i1).isEqualTo(1);

        int i2 = uploadRepository.movieDelete(movieinfo);
        assertThat(i2).isEqualTo(1);
    }

}