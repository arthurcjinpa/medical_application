//package medical_analytical_prescription.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "symptoms")
//@Data
//public class Symptom {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "symptom_id_seq")
//    @SequenceGenerator(name = "symptom_id_seq", schema = "public", allocationSize = 1)
//    private Long id;
//
//    @Column
//    private String name;
//
//    @ManyToOne
//    private Application application;
//
//}
