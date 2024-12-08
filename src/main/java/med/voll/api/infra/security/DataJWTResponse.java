package med.voll.api.infra.security;

/**
 * A record that holds the JWT token response.
 * @param tokenJWT the JWT token
 */
public record DataJWTResponse(String tokenJWT) {}