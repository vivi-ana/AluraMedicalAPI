package med.voll.api.domain.doctor;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.address.DataAddress;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.DataAppointmentSchedule;
import med.voll.api.domain.patient.DataPatientRegister;
import med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("It should return null when the searched doctor exists but is not available on that date.")
    void selectRandomAvailableDoctorByDateScenery1() {
        //given or arrange
        var nextMonday10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = registerDoctor("Doctor1", "doctor@gmail.com", "12345", Specialty.CARDIOLOGY);
        var patient = registerPatient("Patient1", "patient@gmail.com", "123789");
        scheduleAppointment(doctor, patient, nextMonday10AM);

        //when or act
        var doctorAvailable = doctorRepository.selectRandomAvailableDoctorByDate(Specialty.CARDIOLOGY, nextMonday10AM);

        //then or assert
        assertThat(doctorAvailable).isNull();
    }

    @Test
    @DisplayName("It should return a doctor when the doctor is available on that date.")
    void selectRandomAvailableDoctorByDateScenery2() {
        //given or arrange
        var nextMonday10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = registerDoctor("Doctor1", "doctor@gmail.com", "12345", Specialty.CARDIOLOGY);

        //when or act
        var doctorAvailable = doctorRepository.selectRandomAvailableDoctorByDate(Specialty.CARDIOLOGY, nextMonday10AM);

        //then or assert
        assertThat(doctorAvailable).isEqualTo(doctor);
    }

    private void scheduleAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String identityCard, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, identityCard, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String identityCard) {
        var patient = new Patient(patientData(name, email, identityCard));
        em.persist(patient);
        return patient;
    }

    private DataDoctorRegister doctorData(String name, String email, String identityCard, Specialty specialty) {
        return new DataDoctorRegister(
                name,
                email,
                "6145489789",
                identityCard,
                specialty,
                addressData()
        );
    }

    private DataPatientRegister patientData(String name, String email, String identityCard) {
        return new DataPatientRegister(
                name,
                email,
                "1234564",
                identityCard,
                addressData()
        );
    }

    private DataAddress addressData() {
        return new DataAddress(
                "street x",
                "district y",
                "city z",
                "123",
                "1"
        );
    }

}