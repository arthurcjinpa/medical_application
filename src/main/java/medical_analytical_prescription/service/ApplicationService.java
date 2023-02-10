package medical_analytical_prescription.service;

import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import medical_analytical_prescription.entity.Application;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {

  List<Application> showAllApplications();

  Application addApplication(PrescriptionConfirmationDto prescriptionConfirmationDto);

  void deleteApplicationById(Long id);

  List<Application> findApplicationsByUserId(Long id);

  Application findApplicationById(Long id);
}
