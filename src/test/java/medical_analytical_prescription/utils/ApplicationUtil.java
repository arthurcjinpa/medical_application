package medical_analytical_prescription.utils;

import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;

import java.time.ZonedDateTime;
import java.util.List;

import static medical_analytical_prescription.enums.ApplicationStatus.IN_PROGRESS;
import static medical_analytical_prescription.enums.Sex.M;

public class ApplicationUtil {

    public Application createApplication() {
        return Application.builder()
                .id(1L)
                .context("context here")
                .status(IN_PROGRESS)
                .symptoms(List.of("symptomNumber1, symptomNumber2"))
                .applicant(createUser())
                .createDate(ZonedDateTime.now())
                .build();
    }

    private User createUser() {
        return User.builder()
                .firstName("user first name")
                .lastName("user last name")
                .age(23)
                .sex(M)
                .email("user@usermail.com")
                .build();
    }
}