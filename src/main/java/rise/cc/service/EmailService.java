package rise.cc.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rise.cc.common.Mail;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Mail mail) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setSubject(mail.getSendTitle());
        simpleMailMessage.setText(mail.getSendContent());
        simpleMailMessage.setFrom(mail.getFrom());
        simpleMailMessage.setTo(mail.getTo());

        javaMailSender.send(simpleMailMessage);
    }
}
