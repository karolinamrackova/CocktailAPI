package project.week0project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.week0project.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
