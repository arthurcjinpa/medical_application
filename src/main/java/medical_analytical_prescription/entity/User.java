package medical_analytical_prescription.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import medical_analytical_prescription.enums.Sex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
  @SequenceGenerator(name = "users_id_seq", schema = "public", allocationSize = 1)
  private Long id;

  @Column private String firstName;

  @Column private String lastName;

  @Column private int age;

  @Column private Sex sex;

  @Column(unique = true)
  private String email;

  @JsonManagedReference
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicant", cascade = CascadeType.ALL)
  private List<Application> applicationHistoryIds = new ArrayList<>();
}
