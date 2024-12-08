package med.voll.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their username.
     * @param username the username of the user
     * @return the user details
     */
    UserDetails findByUserName(String username);
}