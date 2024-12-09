package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator to ensure the patient does not have another appointment on the same day.
 */
@Component
public class ValidatorPatientSameDayAppointment  implements ValidatorAppointment {

    @Autowired
    AppointmentRepository appointmentRepository;

    /**
     * Validates that the patient does not have another appointment on the same day.
     * @param dataAppointmentSchedule the data of the appointment to be scheduled
     * @throws ValidatorException if the patient has another appointment on the same day
     */
    public void valid(DataAppointmentSchedule dataAppointmentSchedule) {
        var firstHour = dataAppointmentSchedule.date().withHour(7);
        var lastHour = dataAppointmentSchedule.date().withHour(18);
        var isAnAppointmentInSameDay = appointmentRepository.existsByPatientIdAndDateBetween(dataAppointmentSchedule.idPatient(), firstHour, lastHour);
        if(isAnAppointmentInSameDay) throw new ValidatorException("The patient already has an appointment.");
    }
}