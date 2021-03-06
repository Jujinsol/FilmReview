package movieReview.review.repository.GetMovieInfo;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.repository.Upload.UploadRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class getMovieRepositoryImplTest {
    @Autowired
    UploadRepositoryImpl uploadRepository;
    movieInfo movieinfo = new movieInfo();

    @Autowired
    getMovieRepository getMovieRepository;

    @AfterEach
    void delete() {
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
    void findMovie() {
        List<JpaMovieInfo> movie = getMovieRepository.findMovie(movieinfo);
        JpaMovieInfo jpaMovieInfo = movie.get(0);
        Assertions.assertAll(
                () -> assertThat(jpaMovieInfo.getMovieName()).isEqualTo("제목"),
                () -> assertThat(jpaMovieInfo.getOpenYear()).isEqualTo(2010),
                () -> assertThat(jpaMovieInfo.getPhotoUri()).isEqualTo("static/---"),
                () -> assertThat(jpaMovieInfo.getPhotoOriName()).isEqualTo("test.png"),
                () -> assertThat(jpaMovieInfo.getDirectorName()).isEqualTo("주진성"),
                () -> assertThat(jpaMovieInfo.getStoryLine()).isEqualTo("줄거리")
        );
    }

    @Test
    void photoName으로_findMovie() {
        JpaMovieInfo jpaMovieInfo = getMovieRepository.EachMovieInfo(movieinfo);
        Assertions.assertAll(
                () -> assertThat(jpaMovieInfo.getMovieName()).isEqualTo("제목"),
                () -> assertThat(jpaMovieInfo.getOpenYear()).isEqualTo(2010),
                () -> assertThat(jpaMovieInfo.getPhotoUri()).isEqualTo("static/---"),
                () -> assertThat(jpaMovieInfo.getPhotoOriName()).isEqualTo("test.png"),
                () -> assertThat(jpaMovieInfo.getDirectorName()).isEqualTo("주진성"),
                () -> assertThat(jpaMovieInfo.getStoryLine()).isEqualTo("줄거리")
        );
    }
}