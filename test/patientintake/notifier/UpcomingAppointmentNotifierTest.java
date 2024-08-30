package patientintake.notifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;


import java.time.LocalDate;


@DisplayName("UpcomingAppointmentNotifier Should")
class UpcomingAppointmentNotifierTest {

    private EmailNotificationTestDouble emailDouble;

    @BeforeEach
    void init(){
    this.emailDouble=new EmailNotificationTestDouble();
    }

    @Test
    @DisplayName("Send notification for tommorrow's appointments")
    public void sendNotification(){
        ClinicCalendar calendar=new ClinicCalendar(LocalDate.of(2024,8,20));
        calendar.addAppointment("jim","fire", "avery","08/21/2024 4:30 pm");

        UpcomingAppointmentNotifier notifier=new UpcomingAppointmentNotifier(calendar,emailDouble);

        notifier.run();

        Assertions.assertEquals(1,emailDouble.receivedMessages.size());
        EmailNotificationTestDouble.Messages messages=emailDouble.receivedMessages.get(0);
        Assertions.assertEquals("firejim@email.com",messages.address);

    }

}