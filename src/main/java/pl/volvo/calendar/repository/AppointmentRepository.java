package pl.volvo.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.volvo.calendar.entity.Appointment;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository <Appointment, Long>{

    @Query("SELECT a FROM Appointment a WHERE a.date BETWEEN :startDate AND :endDate ORDER BY a.date")
    List<Appointment> getAllBetweenDates(@Param("startDate")OffsetDateTime startDate, @Param("endDate")OffsetDateTime endDate);

    List<Appointment> getAppointmentByOrganizer(String organizer);
}
