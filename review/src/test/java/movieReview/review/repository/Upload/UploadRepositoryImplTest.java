package movieReview.review.repository.Upload;

import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.controller.Join.JoinForm;
import movieReview.review.repository.Join.joinRepository;
import movieReview.review.repository.Join.joinRepositoryImpl;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepository;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepositoryImpl;
import movieReview.review.service.Join.joinService;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UploadRepositoryImplTest {
    @Autowired
    UploadRepositoryImpl uploadRepository;
    movieInfo movieinfo = new movieInfo();
    JoinForm joinForm = new JoinForm();
    ReviewInfo review = new ReviewInfo();

    @Autowired
    ReviewFunctionRepository reviewUpload;

    @Autowired
    joinService joinService;

    String randID = UUID.randomUUID().toString();
    String TestMovieName = "testesteste";

    @AfterEach
    void delete() {
        int i = uploadRepository.movieDelete(movieinfo);
        assertThat(i).isEqualTo(1);
    }

    @BeforeEach
    void insert() {

        movieinfo.setPhotoOriName(randID);
        movieinfo.setPhotoUri("static/-----");
        movieinfo.setStoryLine("줄거리");
        movieinfo.setMovieName(TestMovieName);
        movieinfo.setOpenYear(2010);
        movieinfo.setDirectorName("주진성");
        movieinfo.setTrailerCode("asdfasdf");
        // when
        int insert = uploadRepository.insert(movieinfo);
        //then
        assertThat(insert).isEqualTo(1);
    }

    @Test
    @DisplayName("영화리뷰 남아있는상태로 영화 삭제시 전체삭제여부 테스트 : 사전준비 , 영화리뷰 작성")
    void deleteTest(){
        joinForm.setEmail("EmailEX@NAVER.COM");
        joinForm.setId("jjsair0412");
        joinForm.setPassword("Password123");

        joinService.join(joinForm);


        review.setReviewUser("jjsair0412");
        review.setMovieReivew("정말재밌어요");
        review.setMoviePoint(10);
        review.setPhotoOriName(movieinfo.getPhotoOriName());

        reviewUpload.insertReview(review);
    }

    @Test
    @DisplayName("실제 삭제시 리뷰도 삭제되는지 테스트")
    void deleteTest_2() throws ClassNotFoundException {

        List<JpaRevieTab> reviewList = reviewUpload.selectReview(review);
        assertThat(reviewList.size()).isEqualTo(0);
    }


    @Test
    void select() {
        //given
        photoUriInfo photoUriinfo = new photoUriInfo();

        photoUriinfo.setPhotoUri("static/-----");
        photoUriinfo.setPhotoOriName(randID);

        //when
        photoUriInfo select = uploadRepository.select(photoUriinfo);
        //then
        Assertions.assertAll(
                () -> assertThat(select.getPhotoUri()).isEqualTo("static/-----"),
                () -> assertThat(select.getPhotoOriName()).isEqualTo(null)
        );
    }

    @Test
    void 영화제목으로포스터원본이름찾기() {
        List<photoUriInfo> myPhotoOriName = uploadRepository.findMyPhotoOriName(movieinfo);
        for (photoUriInfo photoUriInfo : myPhotoOriName) {
            assertThat(photoUriInfo.getPhotoOriName()).isEqualTo(randID);
        }
    }

    private static joinRepository join;

    @Autowired
    public UploadRepositoryImplTest(joinRepository joinRepository){
        this.join = joinRepository;
    }

    @AfterAll
    static void 회원가입해놓은거_삭제(){
        userInfo userInfo = new userInfo();
        userInfo.setId("jjsair0412");
        int delete = join.delete(userInfo);
        assertThat(delete).isEqualTo(1);
    }
}