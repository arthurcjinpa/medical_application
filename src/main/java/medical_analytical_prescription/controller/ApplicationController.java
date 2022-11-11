package medical_analytical_prescription.controller;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/list")
    public List<Application> allApplications() {
        return applicationService.showAllApplications();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping("/add")
    public void addApplication(@RequestBody Application application) {
        applicationService.addApplication(application);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteApplications(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
    }

    @GetMapping("/user/{id}")
    public List<Application> findApplicationsByUserId(@PathVariable Long id) {
        return applicationService.findApplicationsByUserId(id);
    }
}