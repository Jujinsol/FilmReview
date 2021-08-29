package movieReview.review.repository.Upload;

import movieReview.review.dto.photoUriInfo;

public interface UploadRepository {
    int insert(photoUriInfo photoUriInfo); // 관리자가 사진 업로드
    photoUriInfo select(photoUriInfo photoUriinfo); // 사진 출력해줄때 사용할 select
}
