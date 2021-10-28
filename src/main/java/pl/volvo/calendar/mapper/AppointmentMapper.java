package pl.volvo.calendar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.volvo.calendar.dto.AppointmentDto;
import pl.volvo.calendar.entity.Appointment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper {

    Appointment appointmentDtoToAppointment(AppointmentDto appointmentDto);
}