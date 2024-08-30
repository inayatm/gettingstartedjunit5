package patientintake.notifier;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendNotificaitons implements EmailNotifier {
    @Override
    public void sendNotification(String subject, String body, String address) {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "localhost");
        Session session = Session.getDefaultInstance(properties, null);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("system@patientintake.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
