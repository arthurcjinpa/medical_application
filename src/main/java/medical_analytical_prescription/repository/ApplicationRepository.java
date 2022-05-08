package medical_analytical_prescription.repository;

import medical_analytical_prescription.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findApplicationByApplicantId(Long id);

}
