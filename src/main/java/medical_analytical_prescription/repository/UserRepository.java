package medical_analytical_prescription.repository;

import medical_analytical_prescription.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
