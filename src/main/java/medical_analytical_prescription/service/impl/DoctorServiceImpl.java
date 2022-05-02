//package medical_analytical_prescription.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import medical_analytical_prescription.entity.Doctor;
//import medical_analytical_prescription.entity.User;
//import medical_analytical_prescription.repository.DoctorRepository;
//import medical_analytical_prescription.service.DoctorService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DoctorServiceImpl implements DoctorService {
//
//    private final DoctorRepository doctorRepository;
//
//    @Override
//    public List<Doctor> showAllDoctors() {
//        return doctorRepository.findAll();
//    }
//
//    @Override
//    public void addDoctor(Doctor doctor) {
//        doctorRepository.save(doctor);
//    }
//}
