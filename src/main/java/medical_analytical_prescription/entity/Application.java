package medical_analytical_prescription.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import medical_analytical_prescription.enums.ApplicationStatus;
import medical_analytical_prescription.utils.StringToListConverter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "APPLICATION")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_id_seq")
  @SequenceGenerator(name = "application_id_seq", schema = "public", allocationSize = 1)
  private Long id;

  @Column private Long doctorId;

  @Column private ZonedDateTime sessionTime;

  @Column private String context;

  @Column private ApplicationStatus status;

  @Column
  @Convert(converter = StringToListConverter.class)
  private List<String> symptoms;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "APPLICANT_ID")
  private User applicant;

  @Column
  @CreationTimestamp
  private ZonedDateTime createDate;
}
