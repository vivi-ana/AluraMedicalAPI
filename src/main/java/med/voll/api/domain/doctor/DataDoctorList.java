package med.voll.api.domain.doctor;

/**
 * A record that holds basic information about a doctor.
 * @param id the unique identifier of the doctor
 * @param name the name of the doctor
 * @param specialty the specialty of the doctor
 * @param identityCard the identity card number of the doctor
 * @param email the email address of the doctor
 */
public record DataDoctorList(Long id, String name, String specialty, String identityCard, String email) {

    /**
     * Constructs a DataDoctorList object using a Doctor instance.
     * @param doctor the Doctor object containing doctor's details
     */
    public DataDoctorList(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getSpecialty().toString(), doctor.getIdentityCard(), doctor.getEmail());
    }
}