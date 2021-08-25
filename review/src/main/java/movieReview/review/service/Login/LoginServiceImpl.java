package movieReview.review.service.Login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.Join.joinRepositoryImpl;
import movieReview.review.repository.Login.LoginRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{
    private LoginRepositoryImpl LoginRepository;
    private joinRepositoryImpl joinRepository;

    @Override
    public userInfo userLogin(userInfo userinfo) {


        return null;
    }

    @Override
    public mangerInfo mangerLogin(mangerInfo mangerinfo) {
        return null;
    }
}
