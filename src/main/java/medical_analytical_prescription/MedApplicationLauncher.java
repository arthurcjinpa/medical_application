package medical_analytical_prescription;

import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.repository.ApplicationRepository;
import medical_analytical_prescription.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import static medical_analytical_prescription.enums.Sex.M;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class MedApplicationLauncher {

  public static void main(String[] args) {
    SpringApplication.run(MedApplicationLauncher.class, args);
  }

  @Bean
  CommandLineRunner runner(
      UserRepository userRepository, ApplicationRepository applicationRepository) {
    return args -> {
      userRepository.save(new User(1L, "Nicolaus", "Copernicus", 24, M, "nicop@mail.com", null));
      userRepository.save(new User(1L, "Galileo", "Galilei", 45, M, "galeo@mail.com", null));
    };
  }
}
