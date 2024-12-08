package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.DataAddress;

/**
 * A record that holds registration data for a new doctor.
 * @param name the name of the doctor, must not be blank
 * @param email the email address of the doctor, must be a valid email
 * @param cellphoneNumber the cellphone number of the doctor, must not be blank
 * @param identityCard the identity card number of the doctor, must match the specified pattern
 * @param specialty the specialty of the doctor, must not be null
 * @param address the address of the doctor, must be valid and not null
 */
public record DataDoctorRegister(
        @NotBlank(message = "{name.required}")
        String name,
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{phone.required}")
        String cellphoneNumber,
        @NotBlank(message = "{identityCard.required}")
        @Pattern(regexp = "\\d{4,6}", message = "{identityCard.invalid}")
        String identityCard,
        @NotNull(message = "{specialty.required}")
        Specialty specialty,
        @NotNull(message = "{address.required}")
        @Valid
        DataAddress address) {
}