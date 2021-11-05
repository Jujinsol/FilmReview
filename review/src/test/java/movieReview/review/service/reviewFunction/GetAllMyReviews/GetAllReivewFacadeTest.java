package movieReview.review.service.reviewFunction.GetAllMyReviews;

import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.Upload.UploadRepositoryImpl;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetAllReivewFacadeTest {

    @Autowired
    ChangeOriginalMovieName allReivewFacade;

    @Autowired
    ReviewFunctionRepository reviewFunctionRepository;

    @Autowired
    UploadRepositoryImpl uploadRepository;

    movieInfo movieinfo = new movieInfo();
    movieInfo movieinfo2 = new movieInfo();

    ReviewInfo reviewInfo = new ReviewInfo();
    ReviewInfo reviewInfo2 = new ReviewInfo();


    @BeforeEach
    void insert() {

        movieinfo.setPhotoOriName("test.png");
        movieinfo.setPhotoUri("static/---");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName("제목");
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");
        movieinfo.setTrailerCode("asdfasdf");

        movieinfo2.setPhotoOriName("test2.png");
        movieinfo2.setPhotoUri("static/---");
        movieinfo2.setStoryLine("줄거리");
        movieinfo2.setMovieName("제목두번째");
        movieinfo2.setOpenYear(2010);
        movieinfo2.setDirectorName("주진성");
        movieinfo2.setTrailerCode("asdfasdf");

        uploadRepository.insert(movieinfo);
        uploadRepository.insert(movieinfo2);

        reviewInfo.setReviewUser("jjsair0412");
        reviewInfo.setMovieReivew("정말재밌어요");
        reviewInfo.setMoviePoint(10);
        reviewInfo.setPhotoOriName("test.png");

        reviewInfo2.setReviewUser("jjsair0412");
        reviewInfo2.setMovieReivew("정말재밌어요증말");
        reviewInfo2.setMoviePoint(12);
        reviewInfo2.setPhotoOriName("test2.png");

        reviewFunctionRepository.insertReview(reviewInfo);
        reviewFunctionRepository.insertReview(reviewInfo2);
    }

    @AfterEach
    void 테스트용더미정보삭제(){
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setReviewUser("imy123");
        reviewInfo.setPhotoOriName("test.png");

        ReviewInfo reviewInfo1 = new ReviewInfo();
        reviewInfo1.setReviewUser("jjsair0412");
        reviewInfo1.setPhotoOriName("test2.png");
        // 리뷰삭제
        reviewFunctionRepository.deleteReview(reviewInfo);
        reviewFunctionRepository.deleteReview(reviewInfo1);

        //영화삭제
        uploadRepository.movieDelete(movieinfo);
        uploadRepository.movieDelete(movieinfo2);
    }

    @Test
    void 리뷰정보들로영화제목들반환하는지체크() throws ClassNotFoundException {

        String[] movieName = allReivewFacade.ChangeMovieName("jjsair0412");
        assertThat(movieName[0]).isEqualTo("제목");
        assertThat(movieName[1]).isEqualTo("제목두번째");
    }
}