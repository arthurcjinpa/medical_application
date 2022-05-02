package medical_analytical_prescription.controller;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("applications/all")
    public List<Application> allApplications() {
        return applicationService.showAllApplications();
    }

    @PostMapping("application/add")
    public void addApplication(@RequestBody Application application) {
        applicationService.addApplication(application);
    }

    @DeleteMapping("applications/delete")
    public void deleteApplications() {
        applicationService.deleteApplications();
    }

}
