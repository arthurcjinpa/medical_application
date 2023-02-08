package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.exception.ApplicationNotFoundException;
import medical_analytical_prescription.feign.PrescriptionClient;
import medical_analytical_prescription.mapper.ApplicationMapper;
import medical_analytical_prescription.repository.ApplicationRepository;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.ApplicationService;
import medical_analytical_prescription.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

  private final UserRepository userRepository;
  private final UserService userService;
  private final ApplicationMapper applicationMapper;
  private final ApplicationRepository applicationRepository;

  @Autowired private PrescriptionClient prescriptionClient;

  public void setPrescriptionClient(PrescriptionClient prescriptionClient) {
    this.prescriptionClient = prescriptionClient;
  }

  @Override
  public List<Application> showAllApplications() {
    return applicationRepository.findAll();
  }

  @Override
  @Transactional
  public Application addApplication(PrescriptionConfirmationDto confirmationDto) {

    if (!confirmationDto.isApproved()) {
      return null;
    }

    User user = userService.getUserById(confirmationDto.getUserId());

    Application application =
        applicationMapper.confirmationDtoAndUserToEntity(confirmationDto, user);

    ResponseEntity<String> responseCode =
        prescriptionClient.prescriptionConfirmation(confirmationDto);

    System.out.println(responseCode.getStatusCode().getReasonPhrase());

    if (!responseCode.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException(
          "Sorry! There was an error with prescription system,"
              + " please try to add the application later.");
    }

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

    applicationRepository.save(application);
    userService.updateUser(user);
  }

  @Override
  public void deleteApplicationById(Long id) {
    applicationRepository.deleteById(id);
  }

  @Override
  public List<Application> findApplicationsByUserId(Long id) {
    return applicationRepository
        .findApplicationByApplicantId(id)
        .orElseThrow(
            () -> {
              throw new ApplicationNotFoundException(
                  "Application with user id " + id + " not found.");
            });
  }

  @Override
  public Application findApplicationById(Long id) {
    return applicationRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new ApplicationNotFoundException("Application with id " + id + " not found.");
            });
  }
}
