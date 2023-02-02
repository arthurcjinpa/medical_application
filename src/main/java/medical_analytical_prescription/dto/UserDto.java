package medical_analytical_prescription.dto;

import lombok.*;
import medical_analytical_prescription.enums.Sex;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
    private String email;
}