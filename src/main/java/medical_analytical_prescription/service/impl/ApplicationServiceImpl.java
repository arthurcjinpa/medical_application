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
import java.util.Optional;

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

        User user = userService.getUserByEmail(application.getApplicant().getEmail())
                .orElseGet(() -> registerUserAndSetApplication(application));

        application.setCreateDate(ZonedDateTime.now());
        applicationRepository.save(application);

        refreshOrCreateApplicationHistoryIds(application, user);

        return applicationRepository.save(application);
    }

    private User registerUserAndSetApplication(Application application) {
        User registeredUser = userService.addUser(application.getApplicant());
        application.setApplicant(registeredUser);
        return registeredUser;
    }

    private void refreshOrCreateApplicationHistoryIds(Application application, User user) {

        if (CollectionUtils.isEmpty(user.getApplicationHistoryIds())) {
            List<Application> applications = new ArrayList<>();
            applications.add(application);
            user.setApplicationHistoryIds(applications);
        } else {
            user.getApplicationHistoryIds().add(application);
        }

        userService.updateUser(user);
    }

    @Override
    public void deleteApplicationById(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public List<Application> findApplicationsByUserId(Long id) {
        return applicationRepository.findApplicationByApplicantId(id)
                .orElseThrow(
                        () -> {
                            throw new ApplicationNotFoundException("Application with user id " + id + " not found.");
                        });
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