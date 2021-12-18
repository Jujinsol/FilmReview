package movieReview.review.service.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class checkMangerOrUser {
    private final joinService joinService;

    public int check(String id){

        Optional<mangerInfo> findManger = Optional.ofNullable(joinService.mangerInfo(id));
        Optional<userInfo> userInfo = Optional.ofNullable(joinService.myInfo(id));

        if(!userInfo.isEmpty()){
            //사용자
            return 1;
        }else if (userInfo.isEmpty()){
            if(!findManger.isEmpty()){
                // 관리자
                return 2;
            }
            // 아예없을때
            return 0;
        }
        return 0;
    }

}
