package medical_analytical_prescription.mapper;

import medical_analytical_prescription.dto.ApplicationDto;
import medical_analytical_prescription.dto.PrescriptionConfirmationDto;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.ZonedDateTime;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = ZonedDateTime.class)
public interface ApplicationMapper {

    @Mapping(target = "applicant", ignore = true)
    Application applicationDtoToEntity(ApplicationDto applicationDto);

    Application prescriptionConfirmationAnUserToEntity(PrescriptionConfirmationDto confirmationDto, User user);

    ApplicationDto entityToApplicationDto(Application application);
}
