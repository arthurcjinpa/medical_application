package medical_analytical_prescription.service.impl;

import medical_analytical_prescription.BaseTest;
import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.exception.ApplicationNotFoundException;
import medical_analytical_prescription.feign.PrescriptionClient;
import medical_analytical_prescription.utils.ApplicationUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static medical_analytical_prescription.enums.ApplicationStatus.IN_PROGRESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Transactional
public class ApplicationServiceImplTest extends BaseTest {

  private ApplicationUtil applicationUtil;

  @Before
  public void before() {
    applicationUtil = new ApplicationUtil();

    PrescriptionClient mockPrescriptionClient = mock(PrescriptionClient.class);
    ResponseEntity<String> mockResponse = new ResponseEntity<>("Success", HttpStatus.OK);
    when(mockPrescriptionClient.prescriptionConfirmation(any(PrescriptionConfirmationDto.class)))
        .thenReturn(mockResponse);
    applicationServiceImpl.setPrescriptionClient(mockPrescriptionClient);
  }

  @Test
  public void showAllApplicationsTest() {
    // given
    int repositorySize = applicationRepository.findAll().size();

    // when
    int serviceSize = applicationService.showAllApplications().size();

    // then
    assertEquals(repositorySize, serviceSize);
  }

  @Test
  public void findApplicationByIdTest() {
    // given
    Application savedApplication = addApplication();

    // when
    Application foundedApplication =
        applicationService.findApplicationById(savedApplication.getId());

    // then
    assertEquals(savedApplication.getApplicant(), foundedApplication.getApplicant());
    assertEquals(savedApplication.getSymptoms(), foundedApplication.getSymptoms());
    assertEquals(savedApplication.getContext(), foundedApplication.getContext());
    assertEquals(savedApplication.getStatus(), foundedApplication.getStatus());
    assertEquals(savedApplication.getCreateDate(), foundedApplication.getCreateDate());
  }

  @Test
  public void findApplicationsByUserIdTest() {
    // given
    Application savedApplication = addApplication();

    // when
    List<Application> foundedApplication =
        applicationService.findApplicationsByUserId(savedApplication.getApplicant().getId());

    // then
    assertEquals(1, foundedApplication.size());

    assertEquals(savedApplication.getApplicant(), foundedApplication.get(0).getApplicant());

    assertEquals(savedApplication.getId(), foundedApplication.get(0).getId());

    assertEquals(savedApplication.getSymptoms(), foundedApplication.get(0).getSymptoms());

    assertEquals(savedApplication.getStatus(), foundedApplication.get(0).getStatus());
  }

  @Test
  public void addApplicationTest() {
    // given
    User user = userService.addUser(createUser());
    PrescriptionConfirmationDto createdConfirmationWithRegisteredUser =
        applicationUtil.createAnotherConfirmationDto(user);

    // when
    Application savedApplicationWithOldUser =
        applicationService.addApplication(createdConfirmationWithRegisteredUser);

    // then
    assertNotNull(savedApplicationWithOldUser.getId());

    assertNotNull(applicationService.findApplicationById(savedApplicationWithOldUser.getId()));

    assertEquals(savedApplicationWithOldUser.getStatus(), IN_PROGRESS);

    assertEquals(
        savedApplicationWithOldUser.getContext(),
        createdConfirmationWithRegisteredUser.getContext());
  }

  @Test(expected = ApplicationNotFoundException.class)
  public void deleteApplicationByIdTest() {
    // given
    Application savedApplication = addApplication();

    // when
    applicationService.deleteApplicationById(savedApplication.getId());

    // then
    assertNotNull(userService.getUserById(savedApplication.getApplicant().getId()));
    assertNull(applicationService.findApplicationById((savedApplication.getId())));
  }

  private User createUser() {
    return User.builder()
        .firstName("first name")
        .lastName("last name")
        .age(99)
        .email("email@mail.com")
        .build();
  }

  private Application addApplication() {
    User user = userService.addUser(createUser());
    PrescriptionConfirmationDto createdConfirmationWithRegisteredUser =
        applicationUtil.createAnotherConfirmationDto(user);

    return applicationService.addApplication(createdConfirmationWithRegisteredUser);
  }
}
