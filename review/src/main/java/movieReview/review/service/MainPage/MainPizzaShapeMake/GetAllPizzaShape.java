package movieReview.review.service.MainPage.MainPizzaShapeMake;

import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetAllPizzaShape {
    void GetAllPizzaShape(Page<JpaMovieInfo> movieList, List<String> pizzaShape) throws ClassNotFoundException;
    // 모든영화의 피자점수 가져오기
}
