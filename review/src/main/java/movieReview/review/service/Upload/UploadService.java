package movieReview.review.service.Upload;

import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;

import java.util.List;

public interface UploadService {
    int create(movieInfo movieinfo);
    int movieDelete(movieInfo movieInfo); // 영화정보 삭제
    List<photoUriInfo> findMoviePhotoOriName(movieInfo movieInfo); // 영화정보제거위한 영화제목으로 원본파일명 반환하는메서드
    photoUriInfo showPhoto(photoUriInfo photoUriinfo); // 등록한 포스터 읽어오기 ( 파일저장경로 )

    String createStoreFileName(String originalFilename);// 서버에 저장할 파일명 생성
    String extractExt(String originalFileName);// .을 기준으로 파일확장자명만 반환
    String getFullPath(String filename);// 파일 저장경로를 반환해준다. 파일저장폴더 + 원본파일명
}
