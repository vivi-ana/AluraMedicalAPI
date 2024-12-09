package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * Repository interface for managing appointments.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    /**
     * Checks if a patient has an appointment between the specified times.
     * @param idPatient the ID of the patient
     * @param firstHour the start time of the interval
     * @param lastHour the end time of the interval
     * @return true if the patient has an appointment, false otherwise
     */
    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);

    /**
     * Checks if a doctor has an appointment at the specified date and time and has not cancelled it.
     * @param idDoctor the ID of the doctor
     * @param date the date and time of the appointment
     * @return true if the doctor has an appointment and has not cancelled it, false otherwise
     */
    Boolean existsByDoctorIdAndDateAndCancellationReasonIsNull(Long idDoctor, LocalDateTime date);
}