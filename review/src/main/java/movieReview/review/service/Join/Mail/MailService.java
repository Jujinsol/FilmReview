package movieReview.review.service.Join.Mail;


import javax.mail.internet.AddressException;

public interface MailService {
    /** 메일 전송
     *  @param subject 제목
     *  @param text 내용
     *  @param from 보내는 메일 주소
     *  @param to 받는 메일 주소
     *  @param filePath 첨부 파일 경로: 첨부파일 없을시 null **/
    boolean send(String subject, String text, String from, String to, String filePath);
    int JoinCodeComparison(String myCode, String serverCode);
    boolean checkEmail(String email) throws AddressException; // 이메일 형식 확인용 메서드
}
