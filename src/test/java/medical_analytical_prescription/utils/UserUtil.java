package medical_analytical_prescription.utils;

import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;

import java.time.ZonedDateTime;
import java.util.List;

import static medical_analytical_prescription.enums.ApplicationStatus.IN_PROGRESS;
import static medical_analytical_prescription.enums.Sex.M;

public class UserUtil {

    public User createUser() {
        return User.builder()
                .firstName("user first name")
                .lastName("user last name")
                .age(23)
                .sex(M)
                .email("user@usermail.com")
                .build();
    }

    public Application createApplication(User applicant) {
        return Application.builder()
                .context("context here")
                .status(IN_PROGRESS)
                .symptoms(List.of("symptomNumber1, symptomNumber2"))
                .applicant(applicant)
                .createDate(ZonedDateTime.now())
                .build();
    }

}
