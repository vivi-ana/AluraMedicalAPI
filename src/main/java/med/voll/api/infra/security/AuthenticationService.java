package med.voll.api.infra.security;

import med.voll.api.domain.user.DataUserAuth;
import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for managing authentication.
 * Provides methods to authenticate users, generate JWT tokens, and load user details.
 */
@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Loads user details by username.
     * @param userName the username of the user
     * @return the UserDetails of the user
     * @throws UsernameNotFoundException if the user with the given username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName);
    }
}