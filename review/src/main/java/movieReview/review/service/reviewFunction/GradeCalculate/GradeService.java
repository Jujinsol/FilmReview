package movieReview.review.service.reviewFunction.GradeCalculate;

public interface GradeService {
    int average(); // 전체평균 구하기
    String pizzaReturn(int average); // 점수에따라 피자 변환
}
