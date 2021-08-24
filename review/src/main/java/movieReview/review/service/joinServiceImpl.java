package movieReview.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.joinRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

            // null이 반환되면 회원가입을 진행해야 하기 때문에 Optional로 감싸준다.
            Optional<userInfo> findUser = Optional.ofNullable(joinRepository.selectMyinfo(userinfo));

            if(findUser.isEmpty()){
                return joinRepository.createUser(userinfo);
            }else{
                return 0;
            }

        }else{
            mangerinfo.setId(id);
            mangerinfo.setPassword(password);
            mangerinfo.setEmail(email);
            mangerinfo.setNumber(mangerNum);

            Optional<mangerInfo> findManger = Optional.ofNullable(joinRepository.selectMangerinfo(mangerinfo));

            if(findManger.isEmpty()){
                return joinRepository.createManger(mangerinfo);
            }else{
                return 0;
            }
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
