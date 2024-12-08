package med.voll.api.domain.patient;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

/**
 * Represents a patient entity with personal information.
 * This entity is mapped to the "patients" table in the database.
 */
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient")
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cellphoneNumber;
    private String identityCard;
    @Embedded
    private Address address;
    private Boolean active;

    /**
     * Constructs a Patient object from the provided DataPatientRegister object.
     * @param dataPatientRegister the DataPatientRegister object containing the patient's registration details
     */
    public Patient(DataPatientRegister dataPatientRegister) {
        this.active = true;
        this.name = dataPatientRegister.name();
        this.email = dataPatientRegister.email();
        this.cellphoneNumber = dataPatientRegister.cellphoneNumber();
        this.identityCard = dataPatientRegister.identityCard();
        this.address = new Address(dataPatientRegister.address());
    }

    /**
     * Updates the patient's information with the provided DataPatientUpdate object.
     * @param patientUpdate the DataPatientUpdate object containing the updated patient's details
     */
    public void updateData(DataPatientUpdate patientUpdate) {
        if (patientUpdate.name() != null) this.name = patientUpdate.name();
        if (patientUpdate.cellphoneNumber() != null) this.cellphoneNumber = patientUpdate.cellphoneNumber();
        if (patientUpdate.address() != null) address.updateData(patientUpdate.address());
    }

    /**
     * Marks the patient as inactive.
     */
    public void logicDelete() {
        this.active = false;
    }
}