package med.voll.api.domain.doctor;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing doctors.
 * Provides methods to handle business logic and data processing for doctor operations.
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Retrieves a paginated list of active doctors.
     *
     * @param pageable the pagination information
     * @return a page of active doctors
     */
    public Page<DataDoctorList> getAllActiveDoctors(Pageable pageable) {
        return doctorRepository.findByActiveTrue(pageable).map(DataDoctorList::new);
    }

    /**
     * Registers a new doctor.
     *
     * @param doctorRegister the data to register a new doctor
     * @return the registered doctor's data
     */
    @Transactional
    public DataResponseDoctor registerDoctor(DataDoctorRegister doctorRegister) {
        Doctor doctor = doctorRepository.save(new Doctor(doctorRegister));
        return new DataResponseDoctor(doctor);
    }

    /**
     * Updates a doctor's information.
     *
     * @param doctorUpdate the data to update the doctor's information
     * @return the updated doctor's data
     */
    @Transactional
    public DataResponseDoctor updateDoctor(DataDoctorUpdate doctorUpdate) {
        Doctor doctor = doctorRepository.getReferenceById(doctorUpdate.id());
        doctor.updateData(doctorUpdate);
        return new DataResponseDoctor(doctor);
    }

    /**
     * Marks a doctor as inactive (logical delete).
     *
     * @param id the ID of the doctor to be deleted
     */
    @Transactional
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.logicDelete();
    }

    /**
     * Retrieves the detailed data of a specific doctor.
     *
     * @param id the ID of the doctor to be retrieved
     * @return the detailed data of the doctor
     */
    public DataResponseDoctor getDoctorById(Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        return new DataResponseDoctor(doctor);
    }
}