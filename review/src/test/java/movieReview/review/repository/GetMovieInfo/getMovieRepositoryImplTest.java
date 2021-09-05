package movieReview.review.repository.GetMovieInfo;

import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.repository.Upload.UploadRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class getMovieRepositoryImplTest {
    getMovieRepositoryImpl movieRepository = new getMovieRepositoryImpl();
    UploadRepositoryImpl uploadRepository = new UploadRepositoryImpl();
    movieInfo movieinfo = new movieInfo();

    @BeforeEach
    void createMovie(){
        movieinfo.setMovieName("영화이름");
        movieinfo.setOpenYear(2012);
        movieinfo.setDirectorName("주진성");
        movieinfo.setPhotoOriName("test.jpg");
        movieinfo.setStoryLine("줄거리123");
        movieinfo.setPhotoUri("http");
    }

    @AfterEach
    void delete(){
        uploadRepository.movieDelete(movieinfo);
    }

    @Test
    void getMovieInfo() {
        //given
        uploadRepository.insert(movieinfo);

        //when
        movieInfo movieInfo = movieRepository.getMovieInfo(movieinfo);

        //then
        assertThat(movieinfo.getMovieName()).isEqualTo("영화이름");
        assertThat(movieinfo.getOpenYear()).isEqualTo(2012);
        assertThat(movieinfo.getDirectorName()).isEqualTo("주진성");
        assertThat(movieinfo.getStoryLine()).isEqualTo("줄거리123");
        assertThat(movieinfo.getPhotoOriName()).isEqualTo("test.jpg");
        assertThat(movieinfo.getPhotoUri()).isEqualTo("http");
    }
}