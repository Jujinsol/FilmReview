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
    /** 메일 전송
     *  @param subject 제목
     *  @param text 내용
     *  @param from 보내는 메일 주소
     *  @param to 받는 메일 주소
     *  @param filePath 첨부 파일 경로: 첨부파일 없을시 null **/
    @Override
    public boolean send(String subject, String text, String from, String to, String filePath) {

        // javax.mail.internet.MimeMessage
        MimeMessage message = javaMailSender.createMimeMessage();


        try {
            // org.springframework.mail.javamail.MimeMessageHelper
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom(from);
            helper.setTo(to);

            // 첨부 파일 처리
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
}
