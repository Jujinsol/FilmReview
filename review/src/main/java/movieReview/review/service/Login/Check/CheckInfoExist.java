package movieReview.review.service.Login.Check;


import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;

import java.util.Optional;

public interface CheckInfoExist {
    userInfo checkUser(String id, Integer password); //유저로그인 전체 정보 확인
    mangerInfo checkManger(String id, Integer password); //관리자로그인 전체 정보 확인

    userInfo userIdCheck(String id); //유저 ID 테이블에 존재하는지 확인
    mangerInfo mangerIdCheck(String id); //관리자 ID 테이블에 존재하는지 확인

    userInfo userPwChcek(Integer password); //유저 PW 테이블에 존재하는지 확인
    mangerInfo mangerPwCheck(Integer password); //관리자 PW 테이블에 존재하는지 확인
}
