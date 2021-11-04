package movieReview.review.service.reviewFunction.GetAllMyReviews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.repository.reviewFunction.FindMovie.ReviewFunctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllMyReviewsImpl implements GetAllMyReviews{
    private final ReviewFunctionRepository reviewReposi;

    @Override
    public List<ReviewInfo> getAllMyReviews(ReviewInfo reviewInfo) {
        return reviewReposi.selectMyReviews(reviewInfo);
    }
}
