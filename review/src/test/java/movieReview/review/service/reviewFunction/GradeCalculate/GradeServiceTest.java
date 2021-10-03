package movieReview.review.service.reviewFunction.GradeCalculate;


import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.MovieInfo.JpaMovieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class GradeServiceTest {

    Map<Integer,JpaRevieTab> jpaRevieTabMap = new HashMap<>();
    List<JpaRevieTab> reviewInfo = new ArrayList<>();

    public int AllPoint;
    Random random = new Random();

    public int MakeRandom(){
        int i = random.nextInt(8);
        AllPoint += i;
        return i;
    }

    public int MakeAvg(){
        int result = (int) AllPoint / 5;
        return result;
    }

    public GradeServiceTest(){
        for(int i = 0; i<5; i++){
            jpaRevieTabMap.put(i, new JpaRevieTab());
        }

        JpaRevieTab one = jpaRevieTabMap.get(0);
        one.setMoviePoint(MakeRandom());
        JpaRevieTab two = jpaRevieTabMap.get(1);
        two.setMoviePoint(MakeRandom());
        JpaRevieTab three = jpaRevieTabMap.get(2);
        three.setMoviePoint(MakeRandom());
        JpaRevieTab four = jpaRevieTabMap.get(3);
        four.setMoviePoint(MakeRandom());
        JpaRevieTab five = jpaRevieTabMap.get(4);
        five.setMoviePoint(MakeRandom());

        reviewInfo.add(one);
        reviewInfo.add(two);
        reviewInfo.add(three);
        reviewInfo.add(four);
        reviewInfo.add(five);
    }

    @Test
    void 전체평균구하기(){

        GradeService gradeService = new GradeServiceImpl(reviewInfo);
        int average = gradeService.average();

        assertThat(average).isEqualTo(MakeAvg());
    }

    @Test
    void 점수에맞게피자경로반환_테스트(){
        GradeService gradeService = new GradeServiceImpl(reviewInfo);
        int average = gradeService.average();
        assertThat(gradeService.pizzaReturn(average)).isEqualTo("/gradePhoto/"+MakeAvg()+".png");
    }

}