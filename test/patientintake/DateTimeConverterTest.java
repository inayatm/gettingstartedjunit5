package patientintake;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Date time converter should")
class DateTimeConverterTest {


    @Nested
    @DisplayName("convert today keyword to datetime when passed ..")
    class TodayStringDateTime{

        @Test
        @DisplayName("correctly")
        void convertTodayStringToDatetime() {
            LocalDateTime result = DateTimeConverter.convertToDateTimeFromString("today 4:30 pm", LocalDate.of(2024, 8, 18));
            assertEquals(LocalDateTime.of(2024, 8, 18, 16, 30), result);

        }

        @Test
        @DisplayName("regardless upper and lower case")
       // @Disabled
        void convertTodayStringRegardlessOfCASEToDatetime() {
            String today="ToDay 4:30 pm";
            LocalDateTime result = DateTimeConverter.convertToDateTimeFromString(today, LocalDate.of(2024, 8, 18));
            assertEquals(LocalDateTime.of(2024, 8, 18, 16, 30), result,"tests 1234"+today);
        }

    }




    @Test
    @DisplayName("convert specific date string  keyword to datetime")
    void convertCorrectSpecificDateStringToDateTime(){
        LocalDateTime result = DateTimeConverter.convertToDateTimeFromString("08/18/2024 4:30 pm", LocalDate.of(2024, 8, 18));
        assertEquals(LocalDateTime.of(2024, 8, 18, 16, 30), result);

    }


    @Test
    @DisplayName("Should throw exeception when pattern is wrong")
    void throwsExceptionWhenPatternisWrong(){

        Throwable exception = assertThrows(RuntimeException.class,
                () -> {
                    LocalDateTime result = DateTimeConverter.convertToDateTimeFromString("08/18/2024 4.30 PM", LocalDate.of(2024, 8, 18));
                });
        assertEquals("Unable to create date time from: [08/18/2024 4.30 PM], please enter with format [M/d/yyyy h:mm a]Text '08/18/2024 4.30 PM' could not be parsed at index 12",exception.getMessage());

    }
}