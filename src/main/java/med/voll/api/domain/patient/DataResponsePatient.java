package med.voll.api.domain.patient;

import med.voll.api.domain.address.DataAddress;

/**
 * A record that holds detailed response data for a patient.
 * @param id the unique identifier of the patient
 * @param name the name of the patient
 * @param email the email address of the patient
 * @param cellphoneNumber the cellphone number of the patient
 * @param identityCard the identity card number of the patient
 * @param address the address of the patient
 */
public record DataResponsePatient(Long id, String name, String email, String cellphoneNumber, String identityCard,
                                  DataAddress address) {
    /**
     * Constructs a DataResponsePatient object using a Patient instance.
     * @param patient the Patient object containing patient's details
     */
    public DataResponsePatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCellphoneNumber(), patient.getIdentityCard(),
                new DataAddress(patient.getAddress().getStreet(), patient.getAddress().getDistrict(),
                        patient.getAddress().getCity(), patient.getAddress().getNumber(), patient.getAddress().getComplement()));
    }
}