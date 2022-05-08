package medical_analytical_prescription.service;

import medical_analytical_prescription.entity.Application;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {

    List<Application> showAllApplications();

    void addApplication(Application application);

    void deleteApplications();

    List<Application> findApplicationsByUserId(Long id);

}
