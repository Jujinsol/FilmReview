package movieReview.review.service.Upload;

import lombok.RequiredArgsConstructor;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.repository.Upload.UploadRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final UploadRepositoryImpl uploadRepository;

    @Override
    public int create(movieInfo movieinfo) {
        return uploadRepository.insert(movieinfo);
    }

    @Override
    public photoUriInfo showPhoto(photoUriInfo photoUriinfo) {
        return uploadRepository.select(photoUriinfo);
    }
}
