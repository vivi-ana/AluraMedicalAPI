package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

/**
 * Service class for managing JWT tokens.
 * Provides methods to generate and verify JWT tokens.
 */
@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    /**
     * Generates a JWT token for the specified user.
     * @param user the user for whom the token is generated
     * @return the generated JWT token
     */
    public String generatedToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generatedExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    /**
     * Retrieves the subject (username) from the specified JWT token.
     * @param token the JWT token
     * @return the subject (username)
     * @throws RuntimeException if the token is null or invalid
     */
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception);
        }
        if (verifier.getSubject() == null) throw new RuntimeException("Invalid verifier");
        return verifier.getSubject();
    }

    /**
     * Generates the expiration date for the JWT token.
     * @return the expiration date as an Instant
     */
    private Instant generatedExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}