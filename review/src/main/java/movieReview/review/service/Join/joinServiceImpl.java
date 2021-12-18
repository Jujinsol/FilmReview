package movieReview.review.service.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import movieReview.review.controller.Join.JoinForm;
import movieReview.review.repository.Join.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class joinServiceImpl implements joinService{

    private final joinRepository joinRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String join(JoinForm joinForm) {
        if (joinForm.getNumber() == null) {
            userInfo userInfo = new userInfo();
            userInfo.setId(joinForm.getId());
            userInfo.setEmail(joinForm.getEmail());
            userInfo.setPassword(passwordEncoder.encode(joinForm.getPassword()));

            userInfo findUser = joinRepository.selectMyinfo(userInfo);

            if (findUser == null) {
                int user = joinRepository.createUser(userInfo);
                switch (user) {
                    case 1:
                        return "사용자";
                    case 0:
                        return "fail";
                }
            } else {
                return "JoinForm.id";
            }
        } else {
            mangerInfo mangerinfo = new mangerInfo();
            mangerinfo.setId(joinForm.getId());
            mangerinfo.setEmail(joinForm.getEmail());
            mangerinfo.setNumber(joinForm.getNumber());
            mangerinfo.setPassword(passwordEncoder.encode(joinForm.getPassword()));

            mangerInfo findmanger = joinRepository.selectMangerinfo(mangerinfo);

            if (findmanger == null) {
                int manger = joinRepository.createManger(mangerinfo);
                switch (manger) {
                    case 1:
                        return "관리자";
                    case 0:
                        return "fail";
                }
            } else {
                return "JoinForm.id";
            }
        }
        return "fail";
    }

    @Override
    public userInfo myInfo(String id) {
        userInfo userinfo = new userInfo();
        userinfo.setId(id);
        return joinRepository.selectMyinfo(userinfo);
    }

    @Override
    public mangerInfo mangerInfo(String id) {
        mangerInfo mangerinfo = new mangerInfo();
        mangerinfo.setId(id);
        return joinRepository.selectMangerinfo(mangerinfo);
    }

    @Override
    public int update(String id,String password) {
        userInfo userinfo = new userInfo();
        userinfo.setId(id);
        userinfo.setPassword(passwordEncoder.encode(password));
        return joinRepository.updateMyinfo(userinfo);
    }

    @Override
    public int mangerUpdate(String id, String password) {
        mangerInfo mangerinfo = new mangerInfo();
        mangerinfo.setId(id);
        mangerinfo.setPassword(passwordEncoder.encode(password));
        return joinRepository.updateMangInfo(mangerinfo);
    }

    // 유저 회원탈퇴
    @Override
    public int delete(String id) {
        userInfo userinfo = new userInfo();
        userinfo.setId(id);
        return joinRepository.delete(userinfo);
    }

    // 관리자 회원탈퇴
    @Override
    public int deleteManger(String id) {
        mangerInfo mangerinfo = new mangerInfo();
        mangerinfo.setId(id);
        return joinRepository.deleteManger(mangerinfo);

    }

}
