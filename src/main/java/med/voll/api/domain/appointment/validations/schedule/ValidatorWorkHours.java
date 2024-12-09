package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.ValidatorException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

/**
 * Validator to ensure the appointment is within business hours.
 */
@Component
public class ValidatorWorkHours implements ValidatorAppointment {
    /**
     * Validates that the appointment is within working hours.
     * @param dataAppointmentSchedule the data of the appointment to be scheduled
     * @throws ValidatorException if the appointment is outside of business hours
     */
    public void valid(DataAppointmentSchedule dataAppointmentSchedule) {
        var date = dataAppointmentSchedule.date();
        var sunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpeningHours = date.getHour() < 7;
        var afterOpeningHours = date.getHour() > 18;
        if (sunday || beforeOpeningHours || afterOpeningHours) {
            throw new ValidatorException("Selected time outside of business hours.");
        }
    }
}