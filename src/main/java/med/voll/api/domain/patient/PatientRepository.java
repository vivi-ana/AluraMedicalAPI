package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing Patient entities.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
    /**
     * Finds all active patient with pagination support.
     * @param pageable the pagination information
     * @return a page of active doctors
     */
    Page<Patient> findByActiveTrue(Pageable pageable);

    /**
     * Finds whether a patient is active by their ID.
     * @param idPatient the ID of the patient
     * @return true if the patient is active, false otherwise
     */
    @Query("""
            Select p.active
            from Patient p
            where p.id = :idPatient
            """)
    Boolean findActiveById(Long idPatient);
}