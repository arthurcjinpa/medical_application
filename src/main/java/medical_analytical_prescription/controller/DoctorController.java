//package medical_analytical_prescription.controller;
//
//import lombok.RequiredArgsConstructor;
//import medical_analytical_prescription.entity.Doctor;
//import medical_analytical_prescription.entity.User;
//import medical_analytical_prescription.service.DoctorService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class DoctorController {
//
//    private final DoctorService doctorService;
//
//    @GetMapping("doctors/all")
//    public List<Doctor> addDoctors() {
//        return doctorService.showAllDoctors();
//    }
//
//    @PostMapping("doctor/add")
//    public void addUser(@RequestBody Doctor doctor) {
//        doctorService.addDoctor(doctor);
//    }
//
//}
