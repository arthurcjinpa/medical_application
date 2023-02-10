package medical_analytical_prescription.controller;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

  private final ApplicationService applicationService;

  @GetMapping("/list")
  public List<Application> allApplications() {
    return applicationService.showAllApplications();
  }

  @GetMapping("/{id}")
  public Application getApplicationById(@PathVariable Long id) {
    return applicationService.findApplicationById(id);
  }

  @PostMapping("/add")
  public Application addApplication(
      @RequestBody PrescriptionConfirmationDto prescriptionConfirmationDto) {
    return applicationService.addApplication(prescriptionConfirmationDto);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteApplications(@PathVariable Long id) {
    applicationService.deleteApplicationById(id);
  }

  @GetMapping("list/user/{id}")
  public List<Application> findApplicationsByUserId(@PathVariable Long id) {
    return applicationService.findApplicationsByUserId(id);
  }
}
