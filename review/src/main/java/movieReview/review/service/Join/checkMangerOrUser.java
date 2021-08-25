package movieReview.review.service.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class checkMangerOrUser {
    private final joinServiceImpl joinService;

    public int check(String id){

        Optional<mangerInfo> findManger = Optional.ofNullable(joinService.mangerInfo(id));
        Optional<userInfo> userInfo = Optional.ofNullable(joinService.myInfo(id));

        if(!userInfo.isEmpty()){
            //사용자
            return 1;
        }else if (userInfo.isEmpty()){
            // 관리자거나 아예 없을경우
            if(!findManger.isEmpty()){
                // 관리자일때
                return 2;
            }
        }
        //아예없을때
        return 0;
    }

}
