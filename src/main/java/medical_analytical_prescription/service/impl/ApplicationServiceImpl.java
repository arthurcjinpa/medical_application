package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.enums.ApplicationStatus;
import medical_analytical_prescription.exception.ApplicationNotFoundException;
import medical_analytical_prescription.mapper.ApplicationMapper;
import medical_analytical_prescription.repository.ApplicationRepository;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.ApplicationService;
import medical_analytical_prescription.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static medical_analytical_prescription.enums.ApplicationStatus.IN_PROGRESS;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;

    @Override
    public List<Application> showAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application addApplication(PrescriptionConfirmationDto confirmationDto) {


        if(!confirmationDto.isApproved()) {
            return null;
        }

        User user = userService.getUserById(confirmationDto.getUserId());

        Application application = applicationMapper.
                prescriptionConfirmationAnUserToEntity(confirmationDto, user);

        ResponseEntity<String> responseCode = prescriptionService.prescriptionConfirmation(confirmationDto);

        if(!responseCode.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("Sorry! There was an error with prescription system," +
                    " please try to add the application later.");
        }

//        User user = userService.checkUsersEmailUniqueness(application);

        refreshOrCreateApplicationHistoryIds(application, user);

        return application;
    }

    private void refreshOrCreateApplicationHistoryIds(Application application, User user) {

        if (CollectionUtils.isEmpty(user.getApplicationHistoryIds())) {
            List<Application> applications = new ArrayList<>();
            applications.add(application);
            user.setApplicationHistoryIds(applications);
        } else {
            user.getApplicationHistoryIds().add(application);
        }

        application.setApplicant(user);
        application.setStatus(IN_PROGRESS);
        applicationRepository.save(application);
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