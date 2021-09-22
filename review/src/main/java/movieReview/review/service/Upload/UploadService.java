package movieReview.review.service.Upload;

import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;

public interface UploadService {
    int create(movieInfo movieinfo);
    photoUriInfo showPhoto(photoUriInfo photoUriinfo); // 등록한 포스터 읽어오기 ( 파일저장경로 )
}
