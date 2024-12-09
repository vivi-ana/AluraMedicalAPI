package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

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

    /**
     * Selects a random available doctor by specialty and date.
     * @param specialty the specialty of the doctor
     * @param date the date and time of the appointment
     * @return a random available doctor
     */
    @Query("""
            select d from Doctor d
            where
            d.active = true
            and d.specialty = :specialty
            and d.id not in(
                select a.doctor.id from Appointment a
                where
                a.date = :date
                and
                a.cancellationReason is null
            )
            order by rand()
            limit 1
            """)
    Doctor selectRandomAvailableDoctorByDate(Specialty specialty, LocalDateTime date);

    /**
     * Finds whether a doctor is active by their ID.
     * @param idDoctor the ID of the doctor
     * @return true if the doctor is active, false otherwise
     */
    @Query("""
            Select d.active
            from Doctor d
            where d.id = :idDoctor
            """)
    Boolean findActiveById(Long idDoctor);
}