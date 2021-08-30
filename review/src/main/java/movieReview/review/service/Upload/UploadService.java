package movieReview.review.service.Upload;

import movieReview.review.dto.FileInfo.photoUriInfo;

public interface UploadService {
    int create(photoUriInfo photoUriInfo);
    photoUriInfo showPhoto(photoUriInfo photoUriinfo); // 등록한 포스터 읽어오기 ( 파일저장경로 )
}
