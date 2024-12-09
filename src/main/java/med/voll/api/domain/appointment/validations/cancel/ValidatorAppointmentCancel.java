package med.voll.api.domain.appointment.validations.cancel;

import med.voll.api.domain.appointment.DataAppointmentCancel;

/**
 * Interface for validating appointment cancellations.
 */
public interface ValidatorAppointmentCancel {
    /**
     * Validates the given appointment cancellation data.
     * @param dataAppointmentCancel the data of the appointment to be cancelled
     */
    void valid(DataAppointmentCancel dataAppointmentCancel);
}
