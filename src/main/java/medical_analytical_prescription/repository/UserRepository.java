package medical_analytical_prescription.repository;

import medical_analytical_prescription.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsUserByEmail(String email);

  Optional<User> getUserByEmail(String email);
}
