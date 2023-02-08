package medical_analytical_prescription.utils;

import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static medical_analytical_prescription.enums.ApplicationStatus.IN_PROGRESS;
import static medical_analytical_prescription.enums.ApplicationStatus.READY_FOR_PRESCRIPTION;
import static medical_analytical_prescription.enums.Sex.M;

public class ApplicationUtil {

  public PrescriptionConfirmationDto createConfirmationDto() {
    return PrescriptionConfirmationDto.builder()
        .context("context here")
        .specialty("GASTROENTEROLOGIST")
        .chosenTime(ZonedDateTime.of(2023, 2, 1, 13, 30, 0, 0, ZoneId.systemDefault()))
        .symptoms(List.of("diarrhea", "sensitivity to light", "jaw pain", "nausea"))
        .userId(createUser().getId())
        .doctorId(2L)
        .approved(true)
        .build();
  }

  public PrescriptionConfirmationDto createAnotherConfirmationDto(User user) {
    return PrescriptionConfirmationDto.builder()
        .context("context there")
        .specialty("ORTHODONTIST")
        .chosenTime(ZonedDateTime.of(2023, 2, 1, 13, 30, 0, 0, ZoneId.systemDefault()))
        .symptoms(List.of("headache", "sensitivity to light"))
        .userId(user.getId())
        .doctorId(4L)
        .approved(true)
        .build();
  }

  public Application createApplication() {
    return Application.builder()
        .context("context here")
        .status(IN_PROGRESS)
        .symptoms(List.of("symptomNumber1, symptomNumber2"))
        .applicant(createUser())
        .createDate(ZonedDateTime.now())
        .build();
  }

  public Application createAnotherApplication(User user) {
    return Application.builder()
        .context("context here")
        .status(READY_FOR_PRESCRIPTION)
        .symptoms(List.of("symptomNumber2, symptomNumber3"))
        .applicant(user)
        .createDate(ZonedDateTime.now())
        .build();
  }

  public User createUser() {
    return User.builder()
        .firstName("user first name")
        .lastName("user last name")
        .age(23)
        .sex(M)
        .email("user@usermail.com")
        .build();
  }
}
