package med.voll.api.domain.patient;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing patients.
 * Provides methods to handle business logic and data processing for patient operations.
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Retrieves a paginated list of active patients.
     * @param pageable the pagination information
     * @return a page of patients
     */
    public Page<DataPatientList> getAllPatients(Pageable pageable) {
        return patientRepository.findByActiveTrue(pageable).map(DataPatientList::new);
    }

    /**
     * Registers a new patient.
     * @param dataPatientRegister the data to register a new patient
     * @return the registered patient's data
     */
    @Transactional
    public DataResponsePatient registerPatient(DataPatientRegister dataPatientRegister) {
        Patient patient = patientRepository.save(new Patient(dataPatientRegister));
        return new DataResponsePatient(patient);
    }

    /**
     * Updates a patient's information.
     * @param dataPatientUpdate the data to update the patient's information
     * @return the updated patient's data
     */
    @Transactional
    public DataResponsePatient updatePatient(DataPatientUpdate dataPatientUpdate) {
        Patient patient = patientRepository.getReferenceById(dataPatientUpdate.id());
        patient.updateData(dataPatientUpdate);
        return new DataResponsePatient(patient);
    }

    /**
     * Marks a patient as inactive (logical delete).
     * @param id the ID of the patient to be deleted
     */
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.logicDelete();
    }

    /**
     * Retrieves the detailed data of a specific patient.
     * @param id the ID of the patient to be retrieved
     * @return the detailed data of the patient
     */
    public DataResponsePatient getPatientById(Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        return new DataResponsePatient(patient);
    }
}