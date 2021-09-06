package movieReview.review.repository.Upload;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class UploadRepositoryImplTest {

    UploadRepositoryImpl uploadRepository = new UploadRepositoryImpl();
    movieInfo movieinfo = new movieInfo();

    @AfterEach
    void delete(){
        int i = uploadRepository.movieDelete(movieinfo);
        assertThat(i).isEqualTo(1);
    }

    @BeforeEach
    void insert() {
        // given

        movieinfo.setPhotoOriName("test.png");
        movieinfo.setPhotoUri("static/---");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName("제목");
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");
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


}