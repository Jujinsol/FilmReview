package movieReview.review.service.Upload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.repository.Upload.UploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadServiceImpl implements UploadService {
    private final UploadRepository uploadRepository;
    @Value("${file.dir}")
    private String fileDir;


    @Override
    public int create(movieInfo movieinfo) {
        return uploadRepository.insert(movieinfo);
    }

    @Override
    public int movieDelete(movieInfo movieInfo) {
        return uploadRepository.movieDelete(movieInfo);
    }

    @Override
    public List<photoUriInfo> findMoviePhotoOriName(movieInfo movieInfo) {
        return uploadRepository.findMyPhotoOriName(movieInfo);
    }


    @Override
    public photoUriInfo showPhoto(photoUriInfo photoUriinfo) {
        return uploadRepository.select(photoUriinfo);
    }

    @Override
    public String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext; // 서버에 저장할 파일명 생성
    }

    @Override
    public String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1); // .을 기준으로 파일확장자명만 반환
    }

    @Override
    public String getFullPath(String filename) {
        return fileDir + filename; // 파일 저장경로를 반환해준다. 파일저장폴더 + 원본파일명
    }

    @Override
    public JpaMovieInfo makeUri(JpaMovieInfo movie) {
        photoUriInfo photoUriinfo = new photoUriInfo();

        photoUriinfo.setPhotoUri(movie.getPhotoUri());
        photoUriinfo.setPhotoOriName(movie.getPhotoOriName());

        photoUriInfo photoUriInfo = showPhoto(photoUriinfo);
        ClassPathResource resource = new ClassPathResource("/moviePhoto/" + photoUriInfo.getPhotoOriName());

        Path path1 = Paths.get(resource.getPath());

        movie.setPhotoUri(path1.toString());
        return movie;
    }

}
