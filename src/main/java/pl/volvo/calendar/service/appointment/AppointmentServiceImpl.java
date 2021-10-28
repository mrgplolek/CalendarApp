package pl.volvo.calendar.service.appointment;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.volvo.calendar.dto.AppointmentDto;
import pl.volvo.calendar.dto.RecurringAppointmentDto;
import pl.volvo.calendar.entity.Appointment;
import pl.volvo.calendar.mapper.AppointmentMapper;
import pl.volvo.calendar.repository.AppointmentRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);

    @Override
    public Appointment postAppointment(AppointmentDto appointmentDto){
        Appointment appointment = appointmentMapper.appointmentDtoToAppointment(appointmentDto);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<?> postRecurringAppointment(RecurringAppointmentDto recurringAppointmentDto){
        List<AppointmentDto> listOfAppointmentsDto = new ArrayList<>();
        List<Appointment> listOfAppointments = new ArrayList<>();
        if (recurringAppointmentDto != null && recurringAppointmentDto.getNumberOfWeeks() > 0){
            for (int i = 0; i < recurringAppointmentDto.getNumberOfWeeks(); i++) {
                listOfAppointmentsDto.add(buildAppointmentDto(recurringAppointmentDto, i));
            }
        }
        if (!listOfAppointmentsDto.isEmpty()){
            listOfAppointmentsDto.forEach(appointmentDto -> {
                Appointment appointment = appointmentMapper.appointmentDtoToAppointment(appointmentDto);
                listOfAppointments.add(appointmentRepository.save(appointment));
            });
            return new ResponseEntity<>(listOfAppointments, HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAppoointemtsBetweendDates(String sDate, String eDate){
        OffsetDateTime startDate = OffsetDateTime.parse(sDate);
        OffsetDateTime endDate = OffsetDateTime.parse(eDate);
        return new ResponseEntity<>(appointmentRepository.getAllBetweenDates(startDate, endDate), HttpStatus.OK);
    }

    private AppointmentDto buildAppointmentDto(RecurringAppointmentDto recurringAppointmentDto, int week){
        return AppointmentDto.builder()
                .organizer(recurringAppointmentDto.getOrganizer())
                .title(recurringAppointmentDto.getTitle())
                .date(recurringAppointmentDto.getDate().plusWeeks(week))
                .build();
    }
}
