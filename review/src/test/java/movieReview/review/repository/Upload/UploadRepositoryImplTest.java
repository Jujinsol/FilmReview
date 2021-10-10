package movieReview.review.repository.Upload;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepository;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UploadRepositoryImplTest {
    @Autowired
    UploadRepositoryImpl uploadRepository;
    movieInfo movieinfo = new movieInfo();

    @AfterEach
    void delete(){
        int i = uploadRepository.movieDelete(movieinfo);
        assertThat(i).isEqualTo(1);
    }

    @BeforeEach
    void insert() {

        movieinfo.setPhotoOriName("test.png");
        movieinfo.setPhotoUri("static/---");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName("제목");
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");
        movieinfo.setTrailerCode("asdfasdf");
        // when
        int insert = uploadRepository.insert(movieinfo);
        //then
        assertThat(insert).isEqualTo(1);
    }

    @Test
    void select() {
        //given
        photoUriInfo photoUriinfo = new photoUriInfo();

        photoUriinfo.setPhotoUri("static/---");
        photoUriinfo.setPhotoOriName("test.png");

        //when
        photoUriInfo select = uploadRepository.select(photoUriinfo);
        //then
        assertThat(select.getPhotoUri()).isEqualTo("static/---");
        assertThat(select.getPhotoOriName()).isEqualTo("test.png");
    }

    @Test
    void 영화제목으로포스터원본이름찾기(){
        List<photoUriInfo> myPhotoOriName = uploadRepository.findMyPhotoOriName(movieinfo);
        for (photoUriInfo photoUriInfo : myPhotoOriName) {
            assertThat(photoUriInfo.getPhotoOriName()).isEqualTo("test.png");
        }
    }
}