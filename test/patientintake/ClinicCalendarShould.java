package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ClinicCalendar Should ")
class ClinicCalendarShould {


    private ClinicCalendar calendar;

    @BeforeAll
    public static void tearUp() {
        System.out.println("before all");

    }

    @BeforeEach
    public void init() {
        calendar = new ClinicCalendar(LocalDate.now());
    }

    @Test
    @DisplayName("Record new appoitnment correctly")
    void allowEntryOfAnAppointment() {


        calendar.addAppointment("Jim", "Weaver", "avery",
                "08/16/2024 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();

        assertNotNull(appointments);
        assertEquals(1, appointments.size());

        PatientAppointment enteredAppt = appointments.get(0);

      /* assertEquals("Jim", enteredAppt.getPatientFirstName());
      assertEquals("Weaver", enteredAppt.getPatientLastName());
      assertEquals(Doctor.avery, enteredAppt.getDoctor());  */

        assertAll(
                () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
                () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
                () -> assertEquals(Doctor.avery, enteredAppt.getDoctor()));

//      assertEquals("9/1/2018 02:00 PM",
//              enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)));US
    }

 @Nested
@DisplayName("return appointments for given day correctly")
 class hasAppointments{

    @Test
    @DisplayName("for today")
    void testLocalDateIfStartsWithToday() {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
       String datetime = "today " + LocalTime.now().format(DateTimeFormatter.ofPattern("h:m a"));
       calendar.addAppointment("test", "test1", "murphy", datetime);

       assertEquals(LocalDateTime.now().format(formatter), formatter.format(calendar.getAppointments().get(0).getAppointmentDateTime()));

    }
    @Test
    void returnTrueIfHasAppointments() {

       calendar.addAppointment("Jim", "Weaver", "avery",
               "08/16/2024 2:00 pm");
       assertTrue(calendar.hasAppointments(LocalDate.of(2024, 8, 16)));

    }

    @Test
    void returnFalseIfHasNoAppointments() {

       assertFalse(calendar.hasAppointments(LocalDate.now()));

    }
 }



    @Test
    void shouldFetchAllTodaysAppointments() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
        calendar.addAppointment("jhon", "peeter", "murphy", "today 4:30 pm");
        calendar.addAppointment("marry", "singh", "avery", "today 6:30 pm");
        calendar.addAppointment("golden", "veer", "johnson", "today 7:30 pm");
        calendar.addAppointment("bheem", "bash", "murphy", LocalDateTime.now().format(formatter));
        calendar.addAppointment("bheem", "bash", "murphy", "08/17/2024 10:30 am");

        assertEquals(4, calendar.performTodaysAllAppointments().size());


    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach .");
    }


    @AfterAll
    public static void tearDown() {
        System.out.println("After all.");
    }


    @Nested
    @DisplayName("UpcomingAppoimentNotifier should do")
    class upcomingAppoimentNotifier {
        @Test
        @DisplayName("return  empty list when there are none ")
        void whenThereAreNone() {

            List<PatientAppointment> upcomingAppoints = calendar.getUpcomingAppointments();
            assertEquals(0, upcomingAppoints.size());

        }

        @Test
        @DisplayName("")
        void whenThereAreAppointments() {
            calendar.addAppointment("bheem", "bash", "murphy", LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
            List<PatientAppointment> upcomingAppoints = calendar.getUpcomingAppointments();
            assertEquals(1, upcomingAppoints.size());

        }


    }

}
