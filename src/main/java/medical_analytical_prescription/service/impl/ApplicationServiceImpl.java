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

        Optional<User> user = userService.getUserByEmail(application.getApplicant().getEmail());

        if (user.isEmpty()) {
            user = Optional.ofNullable(userService.addUser(application.getApplicant()));
        }

        application.setCreateDate(ZonedDateTime.now());
        applicationRepository.save(application);

        refreshApplicationHistoryIds(application, user);

        application.setApplicant(user.get());

        return applicationRepository.save(application);
    }

    private void refreshApplicationHistoryIds(Application application, Optional<User> user) {

        if (CollectionUtils.isEmpty(user.get().getApplicationHistoryIds())) {
            List<Application> applications = new ArrayList<>();
            applications.add(application);
            user.get().setApplicationHistoryIds(applications);
        } else {
            user.get().getApplicationHistoryIds().add(application);
        }

        userRepository.save(user.get());
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