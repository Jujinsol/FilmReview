package movieReview.review.service.Upload;

import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;

import java.util.List;

public interface UploadService {
    int create(movieInfo movieinfo);
    int movieDelete(movieInfo movieInfo); // 영화정보 삭제
    List<photoUriInfo> findMoviePhotoOriName(movieInfo movieInfo); // 영화정보제거위한 영화제목으로 원본파일명 반환하는메서드
    photoUriInfo showPhoto(photoUriInfo photoUriinfo); // 등록한 포스터 읽어오기 ( 파일저장경로 )
}
