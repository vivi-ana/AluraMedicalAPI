package med.voll.api.domain.patient;

/**
 * A record that holds basic information about a patient.
 * @param name the name of the patient
 * @param email the email address of the patient
 * @param identityCard the identity card number of the patient
 */
public record DataPatientList (String name, String email, String identityCard) {

    /**
     * Constructs a DataPatientList object using a Patient instance.
     * @param patient the Patient object containing patient's details
     */
    public DataPatientList(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getIdentityCard());
    }
}