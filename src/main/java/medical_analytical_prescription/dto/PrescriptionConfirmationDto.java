package medical_analytical_prescription.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionConfirmationDto {
  private String context;
  private List<String> symptoms;
  private Long doctorId;
  private String specialty;
  private String userEmail;
  private ZonedDateTime chosenTime;
  private boolean approved;
}
