package medical_analytical_prescription.repository;

import medical_analytical_prescription.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
