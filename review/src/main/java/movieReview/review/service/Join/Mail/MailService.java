package movieReview.review.service.Join.Mail;


import javax.mail.internet.AddressException;

public interface MailService {
    boolean send(String code, String from, String to, String filePath); // 메일보내는 메서드
    int JoinCodeComparison(String myCode, String serverCode);    // 인증코드 비교
    boolean checkEmail(String email) throws AddressException; // 이메일 형식 확인용 메서드
    String checkCode(String code); // 인증코드 검증용 메서드
}
