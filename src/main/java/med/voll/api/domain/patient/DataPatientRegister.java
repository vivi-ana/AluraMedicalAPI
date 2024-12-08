package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.DataAddress;

/**
 * A record that holds registration data for a new patient.
 * @param name the name of the patient, must not be blank
 * @param email the email address of the patient, must be a valid email
 * @param cellphoneNumber the cellphone number of the patient, must not be blank
 * @param identityCard the identity card number of the patient, must not be blank
 * @param address the address of the patient, must be valid and not null
 */
public record DataPatientRegister(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String cellphoneNumber,
        @NotBlank String identityCard,
        @NotNull @Valid DataAddress address
) {}