package movieReview.review.service.Login.Check;


import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;

public interface CheckInfoExist {
    loginUserInfo checkUser(String id, Integer password); //유저로그인 전체 정보 확인
    loginMangerInfo checkManger(String id, Integer password); //관리자로그인 전체 정보 확인

    loginUserInfo userIdCheck(String id); //유저 ID 테이블에 존재하는지 확인
    loginMangerInfo mangerIdCheck(String id); //관리자 ID 테이블에 존재하는지 확인

    loginUserInfo userPwChcek(Integer password); //유저 PW 테이블에 존재하는지 확인
    loginMangerInfo mangerPwCheck(Integer password); //관리자 PW 테이블에 존재하는지 확인
}
