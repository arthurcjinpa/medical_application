package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.exception.ApplicationNotFoundException;
import medical_analytical_prescription.repository.ApplicationRepository;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        User user = userRepository.getUserByEmail(application.getApplicant().getEmail());

        if (userRepository.existsUserByEmail(application.getApplicant().getEmail())
                && user != null && !CollectionUtils.isEmpty(user.getApplicationHistoryIds())) {
            user.getApplicationHistoryIds().add(application);
        } else if(user != null && userRepository.existsUserByEmail(application.getApplicant().getEmail())){
            application.getApplicant().setApplicationHistoryIds(applications);
            userRepository.save(user);
        } else {
            user = application.getApplicant();
            application.getApplicant().setApplicationHistoryIds(applications);
            userRepository.save(user);
        }
        application.setApplicant(user);

        application.setCreateDate(LocalDateTime.now());
        applicationRepository.save(application);
    }

    @Override
    public void deleteApplicationById(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public List<Application> findApplicationsByUserId(Long id) {
        return applicationRepository.findApplicationByApplicantId(id);
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(
                        () -> {
                            throw new ApplicationNotFoundException("Application with id " + id + " not found.");
                        });
    }
}