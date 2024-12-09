package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

/**
 * Record representing the data for scheduling an appointment.
 */
public record DataAppointmentSchedule(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty) {
}