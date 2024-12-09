package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

/**
 * Record representing the details of an appointment.
 */
public record DataAppointmentDetail(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
    /**
     * Constructs a new DataAppointmentDetail from an Appointment object.
     * @param appointment the appointment object
     */
    public DataAppointmentDetail(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}