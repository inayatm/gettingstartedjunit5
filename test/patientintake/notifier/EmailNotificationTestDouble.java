package patientintake.notifier;

import java.util.ArrayList;
import java.util.List;

public class EmailNotificationTestDouble implements EmailNotifier {

   public List<Messages> receivedMessages=new ArrayList<>();
    @Override
    public void sendNotification(String subject, String body, String address) {
        receivedMessages.add(new Messages(subject,body,address));
    }


    class Messages{
        public String subject, body,address;

        public Messages(String subject, String body, String address) {
            this.subject = subject;
            this.body = body;
            this.address = address;
        }
    }
}
