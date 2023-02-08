package medical_analytical_prescription.mapper;

import medical_analytical_prescription.dto.UserDto;
import medical_analytical_prescription.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.time.ZonedDateTime;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    imports = ZonedDateTime.class)
public interface UserMapper {

  User userDtoToEntity(UserDto userDto);
}
