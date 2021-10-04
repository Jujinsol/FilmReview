package movieReview.review.service.reviewFunction.UserDuplicateCheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserDuplicateServiceImpl implements UserDuplicateCheckService{

    @Override
    public boolean DuplicateCheck(String id, String reviewUsers){
        if(reviewUsers.contains(id)){
            return true;
        }else{
            return false;
        }
    }

}
