package movieReview.review.service.Login.Check;


import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;

public interface CheckInfoExist {
    loginDto checkUser(String id, String password); //유저로그인 전체 정보 확인
    loginDto checkManger(String id, String password); //관리자로그인 전체 정보 확인
}
