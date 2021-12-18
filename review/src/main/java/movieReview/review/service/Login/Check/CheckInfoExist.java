package movieReview.review.service.Login.Check;


import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;

public interface CheckInfoExist {
    loginDto checkUser(String id, String password); //유저로그인 전체 정보 확인
    loginDto checkManger(String id, String password); //관리자로그인 전체 정보 확인

    loginDto userIdCheck(String id); //유저 ID 테이블에 존재하는지 확인
    loginDto mangerIdCheck(String id); //관리자 ID 테이블에 존재하는지 확인

    loginDto userPwChcek(String password); //유저 PW 테이블에 존재하는지 확인
    loginDto mangerPwCheck(String password); //관리자 PW 테이블에 존재하는지 확인
}
