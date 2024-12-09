package med.voll.api.domain.appointment.validations.cancel;

import med.voll.api.domain.ValidatorException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataAppointmentCancel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Validator for ensuring appointments are cancelled at least 24 hours in advance.
 */
@Component("ValidatorAppointmentCancelInAdvance")
public class ValidatorAppointmentInAdvance implements ValidatorAppointmentCancel {
    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Validates that the appointment can be cancelled with at least 24 hours' notice.
     * @param dataAppointmentCancel the data of the appointment to be cancelled
     * @throws ValidatorException if the appointment cannot be cancelled with 24 hours' notice
     */
    @Override
    public void valid(DataAppointmentCancel dataAppointmentCancel) {
        var date = appointmentRepository.getReferenceById(dataAppointmentCancel.idAppointment());
        var now = LocalDateTime.now();
        var hoursDifference = Duration.between(now, date.getDate()).toHours();
        if (hoursDifference < 24) throw new ValidatorException("The appointment can only be canceled with a minimum of 24 hours' notice");
    }
}