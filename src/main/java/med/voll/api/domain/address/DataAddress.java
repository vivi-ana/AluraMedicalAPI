package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;

/**
 * A record that holds address data.
 * @param street the street name, must not be blank
 * @param district the district name, must not be blank
 * @param city the city name, must not be blank
 * @param number the number of the address
 * @param complement additional address information
 */
public record DataAddress(
        @NotBlank
        String street,
        @NotBlank
        String district,
        @NotBlank
        String city,
        String number,
        String complement) {
}