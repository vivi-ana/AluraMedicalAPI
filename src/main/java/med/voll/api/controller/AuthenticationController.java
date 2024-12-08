package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.DataUserAuth;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.DataJWTResponse;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing authentication.
 * Provides endpoints for user authentication and token generation.
 */
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * Authenticates a user and generates a JWT token.
     * @param dataUserAuth the data for user authentication
     * @return the response entity containing the JWT token
     */
    @PostMapping
    public ResponseEntity authenticationUser(@RequestBody @Valid DataUserAuth dataUserAuth) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataUserAuth.userName(), dataUserAuth.pass());
        var userAuthenticated = authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.generatedToken((User) userAuthenticated.getPrincipal());
        return ResponseEntity.ok(new DataJWTResponse(tokenJWT));
    }
}