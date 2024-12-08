package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

/**
 * Represents a doctor entity with personal and professional information.
 * This entity is mapped to the "doctors" table in the database.
 */
@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Doctor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String email;
    private String cellphoneNumber;
    private String identityCard;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    /**
     * Constructs a Doctor object from the provided DataDoctorRegister object.
     * @param dataDoctorRegister the DataDoctorRegister object containing the doctor's registration details
     */
    public Doctor(DataDoctorRegister dataDoctorRegister) {
        this.name = dataDoctorRegister.name();
        this.email = dataDoctorRegister.email();
        this.cellphoneNumber = dataDoctorRegister.cellphoneNumber();
        this.identityCard = dataDoctorRegister.identityCard();
        this.active = true;
        this.specialty = dataDoctorRegister.specialty();
        this.address = new Address(dataDoctorRegister.address());
    }

    /**
     * Updates the doctor's information with the provided DataDoctorUpdate object.
     * @param doctorUpdate the DataDoctorUpdate object containing the updated doctor's details
     */
    public void updateData(DataDoctorUpdate doctorUpdate) {
        if (doctorUpdate.name() != null) this.name = doctorUpdate.name();
        if (doctorUpdate.identityCard() != null) this.identityCard = doctorUpdate.identityCard();
        if (doctorUpdate.dataAddress() != null)  this.address = address.updateData(doctorUpdate.dataAddress());

    }

    /**
     * Marks the doctor as inactive.
     */
    public void logicDelete() {
        this.active = false;
    }
}