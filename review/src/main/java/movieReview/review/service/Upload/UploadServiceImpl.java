package movieReview.review.service.Upload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.repository.Upload.UploadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadServiceImpl implements UploadService {
    private final UploadRepository uploadRepository;

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

}
