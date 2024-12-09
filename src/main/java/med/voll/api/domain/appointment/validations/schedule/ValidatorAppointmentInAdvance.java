package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.ValidatorException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Validator to ensure appointments are scheduled at least 30 minutes in advance.
 */
@Component("ValidatorAppointmentScheduleInAdvance")
public class ValidatorAppointmentInAdvance implements ValidatorAppointment {

    /**
     * Validates that the appointment time is at least 30 minutes in advance.
     * @param dataAppointmentSchedule the data of the appointment to be scheduled
     * @throws ValidatorException if the appointment is scheduled less than 30 minutes in advance
     */
    public void valid(DataAppointmentSchedule dataAppointmentSchedule) {
        var date = dataAppointmentSchedule.date();
        var now = LocalDateTime.now();
        var minutesDifference = Duration.between(now, date).toMinutes();
        if (minutesDifference < 30) throw new ValidatorException("The appointment time must be selected at least 30 minutes in advance.");
    }
}