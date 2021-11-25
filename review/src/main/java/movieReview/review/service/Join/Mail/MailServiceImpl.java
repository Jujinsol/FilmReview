package movieReview.review.service.Join.Mail;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService{
    private final JavaMailSender javaMailSender;

    @Override
    public boolean send(String code, String from, String to, String filePath) {

        // JavaMailSender의 createMimeMessage 사용
        MimeMessage message = javaMailSender.createMimeMessage();


        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            // 메일 제목
            helper.setSubject("회원가입 인증 코드 발급 안내 입니다.");
            // 메일 내용
            helper.setText("귀하의 인증 코드는 " + code + " 입니다.", true);
            // 보내는 메일 주소
            helper.setFrom(from);
            // 받는 메일 주소
            helper.setTo(to);

            // 첨부 파일 처리
            // 첨부파일이 없다면 NULL 처리
            if (filePath != null) {
                File file = new File(filePath);
                if (file.exists()) {
                    helper.addAttachment(file.getName(), new File(filePath));
                }
            }

            javaMailSender.send(message);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int JoinCodeComparison(String myCode, String serverCode) {
        if(myCode.equals(serverCode)){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public boolean checkEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @Override
    public String checkCode(String code) {
        if(code == null || code.isBlank()){ // null이거나 비어있을 경우
            return "false";
        }else if(code.contains(" ")){ // 공백이 포함되었을 경우
            StringBuilder br = new StringBuilder();
            String[] splitResult = code.split(" ");
            for(int i = 0; i<splitResult.length; i++){
                br.append(splitResult[i]);
            }
            return br.toString();
        }
        return code;
    }
}
