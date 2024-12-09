package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator to ensure the doctor does not have another appointment at the same date and time.
 */
@Component
public class ValidatorDoctorSameDayAppointment implements ValidatorAppointment {
    @Autowired
    AppointmentRepository appointmentRepository;

    /**
     * Validates that the doctor does not have another appointment at the same date and time.
     * @param dataAppointmentSchedule the data of the appointment to be scheduled
     * @throws ValidatorException if the doctor has another appointment at the same date and time
     */
    public void valid(DataAppointmentSchedule dataAppointmentSchedule) {
        var isAnAppointmentInSameDay = appointmentRepository.existsByDoctorIdAndDateAndCancellationReasonIsNull(dataAppointmentSchedule.idDoctor(), dataAppointmentSchedule.date());
        if(isAnAppointmentInSameDay) throw new ValidatorException("The doctor already has another appointment at that date and time.");
    }
}