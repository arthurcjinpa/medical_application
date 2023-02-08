package medical_analytical_prescription.feign;

import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("med-prescription")
public interface PrescriptionClient {

  @RequestMapping("/prescription/confirmation")
  ResponseEntity<String> prescriptionConfirmation(
      @RequestBody PrescriptionConfirmationDto prescriptionConfirmationDto);
}
