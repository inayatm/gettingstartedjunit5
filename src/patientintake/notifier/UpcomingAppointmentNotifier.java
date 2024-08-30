package patientintake.notifier;

import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UpcomingAppointmentNotifier {

    private ClinicCalendar calendar;
    private EmailNotifier notifier;

    UpcomingAppointmentNotifier(ClinicCalendar calendar,EmailNotifier notifier){
        this.calendar=calendar;
        this.notifier=notifier;
    }

    public void run(){

        calendar.getAppointmentsForTomorrow().forEach(appt->
                sendEmailNotificationForAppointment(appt));
    }

    private void sendEmailNotificationForAppointment(PatientAppointment appt) {
        String email=appt.getPatientLastName()+appt.getPatientFirstName()+"@email.com";
        String builBody=buildEmailBody(appt);
        notifier.sendNotification("Appointment Reminder", builBody, email);
    }

    private String buildEmailBody(PatientAppointment appt) {

        return "You have an appointment tomorrow at "
                + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
                + " with Dr. "
                + appt.getDoctor().getName() + ".";
    }


}
