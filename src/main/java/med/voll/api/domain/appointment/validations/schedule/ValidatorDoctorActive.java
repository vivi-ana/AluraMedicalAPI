package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.ValidatorException;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator to ensure the appointment is associated with an active doctor.
 */
@Component
public class ValidatorDoctorActive implements ValidatorAppointment {

    @Autowired
    DoctorRepository doctorRepository;

    /**
     * Validates that the appointment is associated with an active doctor.
     * @param dataAppointmentSchedule the data of the appointment to be scheduled
     * @throws ValidatorException if the doctor is not active
     */
    public void valid(DataAppointmentSchedule dataAppointmentSchedule) {
        if(dataAppointmentSchedule.idDoctor() == null) {
            return;
        }
        var isDoctorActive = doctorRepository.findActiveById(dataAppointmentSchedule.idDoctor());
        if(!isDoctorActive) throw new ValidatorException("The appointment must be associated with an active doctor.");
    }
}