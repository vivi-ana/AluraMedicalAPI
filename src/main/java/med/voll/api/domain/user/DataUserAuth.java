package med.voll.api.domain.user;

/**
 * A record that holds authentication data for a user.
 * @param userName the username of the user
 * @param pass the password of the user
 */
public record DataUserAuth(String userName, String pass) {
}