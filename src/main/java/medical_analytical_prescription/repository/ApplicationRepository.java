package medical_analytical_prescription.repository;

import medical_analytical_prescription.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<List<Application>> findApplicationByApplicantId(Long id);

}
