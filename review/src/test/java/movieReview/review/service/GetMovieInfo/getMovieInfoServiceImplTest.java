package movieReview.review.service.GetMovieInfo;

import movieReview.review.dto.MovieInfo.JpaMovieInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.repository.GetMovieInfo.getMovieRepository;
import movieReview.review.repository.GetMovieInfo.getMovieRepositoryImpl;
import movieReview.review.repository.Upload.UploadRepository;
import movieReview.review.service.Upload.UploadService;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class getMovieInfoServiceImplTest {
    @Autowired
    getMovieRepository getMovieRepository;
    @Autowired
    getMovieInfoService getMovieInfoService;

    @Autowired
    UploadRepository uploadRepository;
    movieInfo movieinfo = new movieInfo();
    movieInfo movieinfo2 = new movieInfo();

    @BeforeEach
    void create(){
        movieinfo.setPhotoOriName("test.png");
        movieinfo.setPhotoUri("static/---");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName("제목");
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");

        movieinfo2.setPhotoOriName("test.png");
        movieinfo2.setPhotoUri("staticasd/---");
        movieinfo2.setStoryLine("줄거리두번쨰");
        movieinfo2.setMovieName("제목");
        movieinfo2.setOpenYear(2012);
        movieinfo2.setDirectorName("주진성두번쨰");


        uploadRepository.insert(movieinfo);
        uploadRepository.insert(movieinfo2);
    }

    @AfterEach
    void delete(){
        uploadRepository.movieDelete(movieinfo);
        uploadRepository.movieDelete(movieinfo2);
    }
    @Test
    void getMovie() {

        //when
        List<JpaMovieInfo> movie = getMovieInfoService.getMovie(movieinfo);

        //then
        assertThat(movie.size()).isEqualTo(2);
    }
}