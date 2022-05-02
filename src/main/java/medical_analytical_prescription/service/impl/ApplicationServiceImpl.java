package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.repository.ApplicationRepository;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public List<Application> showAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public void addApplication(Application application) {
        List<Application> applications = new ArrayList<>();
        applications.add(application);

        application.getApplicant().setApplicationHistoryIds(applications);

        userRepository.save(application.getApplicant());

        application.setCreateDate(LocalDateTime.now());
        applicationRepository.save(application);
    }

    @Override
    public void deleteApplications() {
        applicationRepository.deleteAll();
    }
}
