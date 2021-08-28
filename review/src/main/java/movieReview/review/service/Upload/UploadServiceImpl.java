package movieReview.review.service.Upload;

import lombok.RequiredArgsConstructor;
import movieReview.review.dto.photoUriInfo;
import movieReview.review.repository.Upload.UploadRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final UploadRepositoryImpl uploadRepository;

    @Override
    public int create(photoUriInfo photoUriinfo) {
        return uploadRepository.insert(photoUriinfo);
    }
}
