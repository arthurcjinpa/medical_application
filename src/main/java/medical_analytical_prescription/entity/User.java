package medical_analytical_prescription.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
// TODO: 29.04.2022 Объединить в @Data и исключить @ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/* TODO: 29.04.2022 Добавить поле IsRegistered, чтобы когда добавляю заявку
, сначала проверить, есть ли такой пользователь (если есть до добавим а не сетим)
 и не создавать ему заново лист историй с заявками */

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", schema = "public", allocationSize = 1)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int age;

    @Column
    private String sex;

    @Column(unique = true)
    private String email;

    //связь
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<Application> applicationHistoryIds;

}
