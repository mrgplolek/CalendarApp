package pl.volvo.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.volvo.calendar.dto.AppointmentDto;
import pl.volvo.calendar.dto.RecurringAppointmentDto;
import pl.volvo.calendar.entity.Appointment;
import pl.volvo.calendar.service.appointment.AppointmentService;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseBody
    public Appointment createAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.postAppointment(appointmentDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Appointment getAppointmentById(@PathVariable Long id){
        return appointmentService.getAppointment(id);
    }

    @GetMapping("/organizer")
    public ResponseEntity<?> getAppointmentsByOrganizer(@RequestParam String organizer){
        return appointmentService.getAppointmentByOrganizer(organizer);
    }

    @PostMapping("/recurring")
    public ResponseEntity<?> createRecurringAppointment(@RequestBody RecurringAppointmentDto recurringAppointmentDto){
        return appointmentService.postRecurringAppointment(recurringAppointmentDto);
    }

    @GetMapping("/betweenDates")
    public ResponseEntity<?> showAppointmentsBetweenDates(@RequestParam String startDate, @RequestParam String endDate){
        return appointmentService.getAppointemtsBetweendDates(startDate, endDate);
    }
}
