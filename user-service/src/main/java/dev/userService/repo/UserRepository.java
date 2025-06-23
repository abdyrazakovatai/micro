package dev.userService.repo;

import dev.commonlib.excetption.NotFoundException;
import dev.userService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User getUsersByEmail(String email) {
        return findByEmail(email).orElseThrow(
                () -> new NotFoundException("User with email:" + email + "not found"));
    }

    boolean existsUserByEmail(String email);
}
