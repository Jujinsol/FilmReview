package movieReview.review.service.Upload;

import lombok.RequiredArgsConstructor;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.repository.Upload.UploadRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final UploadRepository uploadRepository;

    @Override
    public int create(movieInfo movieinfo) {
        return uploadRepository.insert(movieinfo);
    }

    @Override
    public photoUriInfo showPhoto(photoUriInfo photoUriinfo) {
        return uploadRepository.select(photoUriinfo);
    }
}
