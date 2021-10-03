package movieReview.review.service.reviewFunction.GradeCalculate;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GradeServiceImpl implements GradeService {

    private List<Integer> requestMoviePoint = new ArrayList<>();
    private List<Integer> AllGrade;
    private String PizzaFilePath = "/gradePhoto/";
    private static Map<Integer, String> PizzaFileList = new HashMap<>();


    public GradeServiceImpl(List<JpaRevieTab> grade) {
        for (JpaRevieTab reviewInfo : grade) {
            requestMoviePoint.add(reviewInfo.getMoviePoint());
        }
        this.AllGrade = requestMoviePoint;

        for (int i = 1; i <= 8; i++) {
            PizzaFileList.put(i, PizzaFilePath + i + ".png");
        }
    }

    public int average() {
        int GradeAverage = 0;
        for (int i = 0; i < AllGrade.size(); i++) {
            Integer integer = AllGrade.get(i);
            GradeAverage += integer;
        }

        int PointSize = AllGrade.size() - 1;
        if (PointSize == 0) {
            return GradeAverage;
        } else {
            return (int) GradeAverage / PointSize;
        }
    }

    public String pizzaReturn(int average) {
        if (average <= 1) {
            return PizzaFileList.get(1);
        } else if (average <= 2) {
            return PizzaFileList.get(2);
        } else if (average <= 3) {
            return PizzaFileList.get(3);
        } else if (average <= 4) {
            return PizzaFileList.get(4);
        } else if (average <= 5) {
            return PizzaFileList.get(5);
        } else if (average <= 6) {
            return PizzaFileList.get(6);
        } else if (average <= 7) {
            return PizzaFileList.get(7);
        } else {
            return PizzaFileList.get(8);
        }
    }

}
