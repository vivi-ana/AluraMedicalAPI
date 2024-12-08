package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
}