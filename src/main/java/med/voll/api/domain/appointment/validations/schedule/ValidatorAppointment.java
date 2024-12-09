package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.DataAppointmentSchedule;

/**
 * Interface for validating appointment scheduling.
 */
public interface ValidatorAppointment {
    /**
     * Validates the given appointment scheduling data.
     * @param dataAppointmentSchedule the data of the appointment to be scheduled
     */
    void valid(DataAppointmentSchedule dataAppointmentSchedule);
}