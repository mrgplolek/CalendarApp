package pl.volvo.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RecurringAppointmentDto {

    private OffsetDateTime date;
    private String title;
    private String organizer;
    private int numberOfWeeks;
}
