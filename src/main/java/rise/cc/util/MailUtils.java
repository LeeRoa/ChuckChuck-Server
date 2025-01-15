package rise.cc.util;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import rise.cc.common.Mail;

import java.util.Properties;

@RequiredArgsConstructor
public class MailUtils {

    private final String host = "risecc.kro.kr";
    private final String port = "2325";

    /**
     * 메일 발송
     * @throws MessagingException 메일 발송 실패
     */
    public void sendEmail(Mail mail) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mail.getFrom()));
        System.out.println("mail.getTo() : " + mail.getTo());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
        message.setSubject(mail.getSendTitle());
        message.setText(mail.getSendContent());

        Transport.send(message);
    }
}
