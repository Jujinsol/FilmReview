package movieReview.review.service.reviewFunction.reviewUploadServie;

import lombok.RequiredArgsConstructor;
import movieReview.review.dto.ReviewInfo.JpaRevieTab;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewUploadServiceImpl implements ReviewUploadService {
    private final ReviewFunctionRepository reviewFunc;

    @Override
    public int reviewUpload(ReviewInfo reviewInfo) {
        return reviewFunc.insertReview(reviewInfo);
    }

    @Override
    public int deleteReview(ReviewInfo reviewInfo) {
        return reviewFunc.deleteReview(reviewInfo);
    }

    @Override
    public List<JpaRevieTab> selectAllReview(ReviewInfo reviewInfo) {
        return reviewFunc.selectReview(reviewInfo);


    }
}
