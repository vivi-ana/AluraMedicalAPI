package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Doctor entities.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Finds all active doctors with pagination support.
     * @param pageable the pagination information
     * @return a page of active doctors
     */
    Page<Doctor> findByActiveTrue(Pageable pageable);
}