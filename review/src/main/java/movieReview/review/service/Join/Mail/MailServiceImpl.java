package movieReview.review.service.Join.Mail;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService{
    private final JavaMailSender javaMailSender;

    @Override
    public boolean send(String subject, String text, String from, String to, String filePath) {

        // JavaMailSender의 createMimeMessage 사용
        MimeMessage message = javaMailSender.createMimeMessage();


        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            // 메일 제목
            helper.setSubject(subject);
            // 메일 내용
            helper.setText(text, true);
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

    // 인증코드 비교
    @Override
    public int JoinCodeComparison(String myCode, String serverCode) {
        if(myCode.equals(serverCode)){
            return 1;
        }else{
            return 0;
        }
    }
}
