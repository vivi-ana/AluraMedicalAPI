package med.voll.api.domain.appointment;

import med.voll.api.domain.appointment.validations.cancel.ValidatorAppointmentCancel;
import med.voll.api.domain.appointment.validations.schedule.ValidatorAppointment;
import med.voll.api.domain.ValidatorException;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing appointments.
 */
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidatorAppointment> validatorAppointmentList;

    @Autowired
    private List<ValidatorAppointmentCancel> validatorAppointmentCancelList;

    /**
     * Schedules a new appointment.
     * @param dataAppointmentSchedule the data for scheduling the appointment
     * @return the details of the scheduled appointment
     * @throws ValidatorException if validation fails
     */
    public DataAppointmentDetail scheduleAppointment(DataAppointmentSchedule dataAppointmentSchedule) {
        if (!patientRepository.existsById(dataAppointmentSchedule.idPatient())) {
            throw new ValidatorException("There is no patient with ID " +dataAppointmentSchedule.idPatient());
        }
        if (dataAppointmentSchedule.idDoctor() !=  null && !doctorRepository.existsById(dataAppointmentSchedule.idDoctor())) {
            throw new ValidatorException("There is no doctor with ID " +dataAppointmentSchedule.idDoctor());
        }

        validatorAppointmentList.forEach(validatorAppointment -> validatorAppointment.valid(dataAppointmentSchedule));

        var doctor = chooseDoctor(dataAppointmentSchedule);
        if (doctor==  null) {
            throw new ValidatorException("There is no doctor available at the selected date and time.");
        }

        var patient = patientRepository.findById(dataAppointmentSchedule.idPatient()).get();
        var appointment = new Appointment(null, doctor, patient, dataAppointmentSchedule.date(), null);

        appointmentRepository.save(appointment);

        return new DataAppointmentDetail(appointment);
    }

    /**
     * Chooses an available doctor based on the provided appointment data.
     * @param dataAppointmentSchedule the data for scheduling the appointment
     * @return the chosen doctor
     * @throws ValidatorException if no doctor is available or specialty is not selected
     */
    private Doctor chooseDoctor(DataAppointmentSchedule dataAppointmentSchedule) {
        if (dataAppointmentSchedule.idDoctor() != null) {
            return doctorRepository.getReferenceById(dataAppointmentSchedule.idDoctor());
        }
        if (dataAppointmentSchedule.specialty() == null) {
            throw new ValidatorException("It is necessary to select a specialty when a doctor is not chosen.");
        }
        return doctorRepository.selectRandomAvailableDoctorByDate(dataAppointmentSchedule.specialty(), dataAppointmentSchedule.date());
    }

    /**
     * Cancels an existing appointment.
     * @param dataAppointmentCancel the data for cancelling the appointment
     * @throws ValidatorException if validation fails or appointment ID does not exist
     */
    public void cancelAppointment(DataAppointmentCancel dataAppointmentCancel) {
        if (!appointmentRepository.existsById(dataAppointmentCancel.idAppointment())) {
            throw new ValidatorException("The provided appointment ID does not exist.");
        }
        validatorAppointmentCancelList.forEach(v -> v.valid(dataAppointmentCancel));

        var appointment = appointmentRepository.getReferenceById(dataAppointmentCancel.idAppointment());
        appointment.cancel(dataAppointmentCancel.reason());
    }
}