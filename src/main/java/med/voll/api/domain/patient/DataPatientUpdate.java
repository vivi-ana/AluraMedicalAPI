package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import med.voll.api.domain.address.DataAddress;

/**
 * A record that holds update data for an existing patient.
 * @param id the unique identifier of the patient
 * @param name the name of the patient * @param email the email address of the patient, must be a valid email
 * @param cellphoneNumber the cellphone number of the patient
 * @param identityCard the identity card number of the patient
 * @param address the updated address of the patient, must be valid
 */
public record DataPatientUpdate(
        Long id,
        String name,
        @Email String email,
        String cellphoneNumber,
        String identityCard,
        @Valid DataAddress address) {
}