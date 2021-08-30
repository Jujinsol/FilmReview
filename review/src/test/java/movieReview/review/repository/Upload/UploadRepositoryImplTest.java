package movieReview.review.repository.Upload;
import movieReview.review.dto.FileInfo.photoUriInfo;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class UploadRepositoryImplTest {

    UploadRepositoryImpl uploadRepository = new UploadRepositoryImpl();

    @Test
    void insert() {
        // given
        photoUriInfo photoUriinfo = new photoUriInfo();

        photoUriinfo.setPhotoUri("static/---");
        photoUriinfo.setPhotoOriName("test.png");
        // when
        int insert = uploadRepository.insert(photoUriinfo);
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