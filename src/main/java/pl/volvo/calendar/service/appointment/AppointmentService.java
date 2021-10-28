package pl.volvo.calendar.service.appointment;

import org.springframework.http.ResponseEntity;
import pl.volvo.calendar.dto.AppointmentDto;
import pl.volvo.calendar.dto.RecurringAppointmentDto;
import pl.volvo.calendar.entity.Appointment;

public interface AppointmentService {

    Appointment postAppointment(AppointmentDto appointmentDto);
    Appointment getAppointment(Long id);
    ResponseEntity<?> postRecurringAppointment(RecurringAppointmentDto recurringAppointmentDto);
    ResponseEntity<?> getAppoointemtsBetweendDates(String sDate, String eDate);
}

