package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.DataAddress;

/**
 * A record that holds update data for an existing doctor.
 * @param id the unique identifier of the doctor, must not be null
 * @param name the name of the doctor
 * @param identityCard the identity card number of the doctor
 * @param dataAddress the updated address of the doctor
 */
public record DataDoctorUpdate(@NotNull Long id, String name, String identityCard, DataAddress dataAddress) {
}