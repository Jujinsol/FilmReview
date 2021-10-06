package movieReview.review.service.MainPage.MainPizzaShapeMake;

import lombok.RequiredArgsConstructor;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeService;
import movieReview.review.service.reviewFunction.GradeCalculate.GradeServiceImpl;
import movieReview.review.service.reviewFunction.reviewUploadServie.ReviewUploadService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPizzaShapeImpl implements GetAllPizzaShape{
    private final ReviewUploadService reviewUploadService;

    @Override
    public void GetAllPizzaShape(Page<JpaMovieInfo> movieList, List<String> pizzaShape) throws ClassNotFoundException {
        int totalPoint = 0;
        for (JpaMovieInfo AllReview : movieList) {
            ReviewInfo reviewInfo = new ReviewInfo();
            reviewInfo.setPhotoOriName(AllReview.getPhotoOriName());
            List<JpaRevieTab> jpaRevieTabs = reviewUploadService.selectAllReview(reviewInfo);

            for (int i = 0; i < jpaRevieTabs.size(); i++) {
                totalPoint = 0;
                if (jpaRevieTabs.size() > 1) {
                    JpaRevieTab jpaRevieTab = jpaRevieTabs.get(i);
                    totalPoint += jpaRevieTab.getMoviePoint();
                } else {
                    JpaRevieTab jpaRevieTab = jpaRevieTabs.get(0);
                    totalPoint += jpaRevieTab.getMoviePoint();
                }
            }
            GradeService GradeInfo = new GradeServiceImpl(totalPoint);
            String pizza = GradeInfo.pizzaReturn(GradeInfo.average());
            pizzaShape.add(pizza); // 전체피자모형 리스트에 추가
        }
    }
}
