package medical_analytical_prescription.feign;

import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class MockPrescriptionClient {

  @RequestMapping("/prescription/confirmation")
  public ResponseEntity<String> prescriptionConfirmation(
      @RequestBody PrescriptionConfirmationDto prescriptionConfirmationDto) {
    return ResponseEntity.ok("It's fine pal");
  }
}
