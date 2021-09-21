package movieReview.review.service.reviewFunction.reviewUploadServie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
    public List<JpaRevieTab> selectAllReview(ReviewInfo reviewInfo) throws ClassNotFoundException {
        return reviewFunc.selectReview(reviewInfo);
    }

}
