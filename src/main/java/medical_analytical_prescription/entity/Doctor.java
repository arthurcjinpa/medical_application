//package medical_analytical_prescription.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "doctor")
//@Data
////наследовать от юзера
//public class Doctor {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_id_seq")
//    @SequenceGenerator(name = "doctor_id_seq", schema = "public", allocationSize = 1)
//    Long id;
//
//    @Column
//    String firstName;
//
//    @Column
//    String lastName;
//
//    @Column
//    String status; //в отпуске или уволен
//
//    @Column
//    String section; //типа терапевт или хирург
//
//}
