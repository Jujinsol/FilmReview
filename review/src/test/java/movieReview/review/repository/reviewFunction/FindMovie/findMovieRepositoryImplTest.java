package movieReview.review.repository.reviewFunction.FindMovie;

import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.Upload.UploadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class findMovieRepositoryImplTest {
    @Autowired
    ReviewFunctionRepository findMovieRepository;
    @Autowired
    UploadRepository uploadRepository;

    ReviewInfo review = new ReviewInfo();
    movieInfo movieinfo = new movieInfo();

    @BeforeEach
    void makeMovie(){
        movieinfo.setPhotoOriName("test.png");
        movieinfo.setPhotoUri("static/---");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName("제목");
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");

        review.setMovieReivew("abcd");
        review.setReviewUser("jjsair0412");
        review.setMoviePoint(1);
        review.setPhotoOriName(movieinfo.getPhotoOriName());

        uploadRepository.insert(movieinfo);


    }

    @Test
    void insertReview() {
        int i = findMovieRepository.insertReview(review);
        assertThat(i).isEqualTo(1);

    }

    @AfterEach
    void deleteReview(){
        int i = findMovieRepository.deleteReview(review);
        assertThat(i).isEqualTo(1);

        int i1 = uploadRepository.movieDelete(movieinfo);
        assertThat(i1).isEqualTo(1);
    }

}