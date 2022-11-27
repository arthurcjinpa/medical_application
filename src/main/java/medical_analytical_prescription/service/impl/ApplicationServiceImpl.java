package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.exception.ApplicationNotFoundException;
import medical_analytical_prescription.repository.ApplicationRepository;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.ApplicationService;
import medical_analytical_prescription.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ApplicationRepository applicationRepository;

    @Override
    public List<Application> showAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application addApplication(Application application) {
        List<Application> applications = new ArrayList<>();
        applications.add(application);

        User user = userRepository.getUserByEmail(application.getApplicant().getEmail());

        if (isUserHaveApplicationHistory(user, application)) {
            user.getApplicationHistoryIds().add(application);
        } else if (isUserHaveNoApplicationHistory(user, application)) {
            user.setApplicationHistoryIds(applications);
            userRepository.save(user);
        } else {
            user = application.getApplicant();
            userService.addUser(user);
            application.getApplicant().setApplicationHistoryIds(applications);
            applicationRepository.save(application);
        }

        application.setApplicant(user);
        application.setCreateDate(ZonedDateTime.now());

        return applicationRepository.save(application);
    }

    private boolean isUserHaveApplicationHistory(User applicant, Application application) {
        return userRepository.existsUserByEmail(application.getApplicant().getEmail())
                && applicant != null && !CollectionUtils.isEmpty(applicant.getApplicationHistoryIds());
    }

    private boolean isUserHaveNoApplicationHistory(User applicant, Application application) {
        return applicant != null && userRepository.existsUserByEmail(application.getApplicant().getEmail());
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