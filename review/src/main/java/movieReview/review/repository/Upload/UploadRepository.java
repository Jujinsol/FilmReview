package movieReview.review.repository.Upload;

import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;

import java.util.List;

public interface UploadRepository {
    int insert(movieInfo movieInfo); // 관리자가 사진 업로드
    photoUriInfo select(photoUriInfo photoUriinfo); // 사진 출력해줄때 사용할 select
    int movieDelete(movieInfo movieInfo); // 영화정보 제거
    List<photoUriInfo> findMyPhotoOriName(movieInfo movieInfo); // 영화정보 삭제위한 원본파일명가져오기
}
