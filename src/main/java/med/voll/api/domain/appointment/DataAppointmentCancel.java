package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

/**
 * Record representing the data for cancelling an appointment.
 */
public record DataAppointmentCancel(
        @NotNull
        Long idAppointment,
        @NotNull
        CancellationReason reason) {
}