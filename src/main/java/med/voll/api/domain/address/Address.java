package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents an address that can be embedded within doctor and patient entity.
 * Provides methods to update address data and constructor overloading for various use cases.
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    /**
     * Constructs an Address object using DataAddress.
     * @param dataAddress the DataAddress object containing address information
     */
    public Address(DataAddress dataAddress) {
        this.street = dataAddress.street();
        this.district = dataAddress.district();
        this.city = dataAddress.city();
        this.number = dataAddress.number();
        this.complement = dataAddress.complement();
    }

    /**
     * Updates the address data with new values.
     * @param dataAddress the DataAddress object containing new address information
     * @return the updated Address object
     */
    public Address updateData(DataAddress dataAddress) {
        this.street = dataAddress.street();
        this.district = dataAddress.district();
        this.city = dataAddress.city();
        this.number = dataAddress.number();
        this.complement = dataAddress.complement();
        return this;
    }
}