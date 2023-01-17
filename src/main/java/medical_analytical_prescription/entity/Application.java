package medical_analytical_prescription.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import medical_analytical_prescription.enums.ApplicationStatus;
import medical_analytical_prescription.utils.StringToListConverter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

//в будущем добавить поле, если прошел сесюрити (регистрацию), то будет поле boolean isClient

@Entity
@Table(name = "APPLICATION")
// TODO: 29.04.2022 Объединить в @Data и исключить @ToString
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

    @Column
    private String context;

    @Column
    private ApplicationStatus status;

    @Column
    @Convert(converter = StringToListConverter.class)
    private List<String> symptoms;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @Column
    private ZonedDateTime createDate;

    //потом сделать связь с врачом, чтоб у врача могли быть заявки юзеров
}
