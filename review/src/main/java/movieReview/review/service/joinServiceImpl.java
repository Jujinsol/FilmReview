package movieReview.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.joinRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class joinServiceImpl implements joinService{

    private final joinRepositoryImpl joinRepository;
    private final userInfo userinfo;
    private final mangerInfo mangerinfo;

    @Override
    public int join(String email, String id, int password, Integer mangerNum) {
        if (mangerNum==null){
            userinfo.setId(id);
            userinfo.setEmail(email);
            userinfo.setPassword(password);

            int userResult = joinRepository.createUser(userinfo);

            return userResult;
        }else{
            mangerinfo.setId(id);
            mangerinfo.setPassword(password);
            mangerinfo.setEmail(email);
            mangerinfo.setNumber(mangerNum);

            return joinRepository.createManger(mangerinfo);
        }
    }

    @Override
    public userInfo myInfo(String id) {
        userinfo.setId(id);

        return joinRepository.selectMyinfo(userinfo);
    }

    @Override
    public int update(String id,int password) {
        userinfo.setId(id);
        userinfo.setPassword(password);

        return joinRepository.updateMyinfo(userinfo);
    }

    @Override
    public int delete(String id, int password) {
        userinfo.setId(id);
        userinfo.setPassword(password);

        return joinRepository.delete(userinfo);
    }
}
