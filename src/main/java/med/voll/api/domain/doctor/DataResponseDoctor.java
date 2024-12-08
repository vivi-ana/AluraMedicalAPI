package med.voll.api.domain.doctor;

import med.voll.api.domain.address.DataAddress;

/**
 * A record that holds detailed response data for a doctor.
 * @param id the unique identifier of the doctor
 * @param name the name of the doctor
 * @param email the email address of the doctor
 * @param cellphoneNumber the cellphone number of the doctor
 * @param identityCard the identity card number of the doctor
 * @param address the address of the doctor
 */
public record DataResponseDoctor(Long id, String name, String email, String cellphoneNumber, String identityCard,
                                 DataAddress address) {

    /**
     * Constructs a DataResponseDoctor object using a Doctor instance.
     * @param doctor the Doctor object containing doctor's details
     */
    public DataResponseDoctor(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCellphoneNumber(), doctor.getIdentityCard(),
                new DataAddress(doctor.getAddress().getStreet(), doctor.getAddress().getDistrict(),
                        doctor.getAddress().getCity(), doctor.getAddress().getNumber(), doctor.getAddress().getComplement()));
    }
}