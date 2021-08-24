package movieReview.review.service.Join;

import lombok.RequiredArgsConstructor;
import movieReview.review.dto.mangerInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class checkMangerOrUser {
    private final joinServiceImpl joinService;

    public int check(String id){

        Optional<mangerInfo> findManger = Optional.ofNullable(joinService.mangerInfo(id));
        if(findManger.isEmpty()){
            // 사용자면 1
            return 1;
        }else{
            // 관리자면 0
            return 0;
        }
    }

}
