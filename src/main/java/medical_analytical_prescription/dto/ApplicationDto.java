package medical_analytical_prescription.dto;

import lombok.*;
import medical_analytical_prescription.enums.ApplicationStatus;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    private String context;
    private ApplicationStatus status;
    private List<String> symptoms;
    private UserDto applicant;
}