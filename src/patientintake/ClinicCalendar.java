package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicCalendar {

   private final List<PatientAppointment> appointments;
   private final LocalDate today;

   public ClinicCalendar(LocalDate today) {
      this.appointments = new ArrayList<>();
      this.today = today;
   }

   public void addAppointment(String patientFirstName, String patientLastName, String doctorKey,
                              String dateTime) {
      Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
      LocalDateTime localDateTime;
      localDateTime = DateTimeConverter.convertToDateTimeFromString(dateTime, today);
      PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName,
              localDateTime, doc);
      appointments.add(appointment);
   }


   public List<PatientAppointment> getAppointments() {
      return this.appointments;
   }

   public boolean hasAppointments(LocalDate localDate) {

      return appointments.stream().anyMatch((appointment -> appointment.getAppointmentDateTime().toLocalDate().equals(localDate)));

   }

   public List<PatientAppointment> performTodaysAllAppointments() {
      return appointments
              .stream()
              .filter(appointment -> appointment.getAppointmentDateTime().toLocalDate().equals(today))
              .collect(Collectors.toList());

   }

   public List<PatientAppointment> getAppointmentsForTomorrow() {
      LocalDate tomorrow = today.plusDays(1);
      return getAppointmentForDate(tomorrow);


   }

   private List<PatientAppointment> getAppointmentForDate(LocalDate tomorrow) {
      return appointments.stream().filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(tomorrow)).collect(Collectors.toList());

   }


   public List<PatientAppointment> getUpcomingAppointments() {

      // appointments.stream().filter(appt->appt.getAppointmentDateTime().toLocalDate().isAfter(today)).collect(Collectors.toList());

      return appointments.stream().filter(appointment -> {
         System.out.println("today "+ today);
         System.out.println(appointment.getAppointmentDateTime().toLocalDate().isAfter(today));
         return appointment.getAppointmentDateTime().toLocalDate().isAfter(today);
      }).collect(Collectors.toList());


   }
}
