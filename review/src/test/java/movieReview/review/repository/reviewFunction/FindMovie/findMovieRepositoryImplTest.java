package movieReview.review.repository.reviewFunction.FindMovie;

import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import movieReview.review.repository.Upload.UploadRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class findMovieRepositoryImplTest {
    @Autowired
    findMovieRepository findMovieRepository;
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

//    @AfterEach
//    void deleteReview(){
//        uploadRepository.movieDelete(movieinfo);
//        int i = findMovieRepository.deleteReview(review);
//        assertThat(i).isEqualTo(1);
//    }
}