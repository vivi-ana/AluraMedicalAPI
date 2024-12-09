package med.voll.api.domain.appointment.validations.schedule;

import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.ValidatorException;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator to ensure the appointment is scheduled for an active patient.
 */
@Component
public class ValidatorPatientActive implements ValidatorAppointment {
    @Autowired
    private PatientRepository patientRepository;

    /**
     * Validates that the patient is active.
     * @param dataAppointmentSchedule the data of the appointment to be scheduled
     * @throws ValidatorException if the patient is not active
     */
    public void valid(DataAppointmentSchedule dataAppointmentSchedule) {
        var isPatientActive = patientRepository.findActiveById(dataAppointmentSchedule.idPatient());
        if (!isPatientActive) throw new ValidatorException("The appointment must be schedule for an active patient.");
    }
}