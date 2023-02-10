package medical_analytical_prescription.controller;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/health")
public class HealthCheckController {

  private final DataSource dataSource;

  @GetMapping
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("UP");
  }

  @GetMapping("/db/liquibase")
  public ResponseEntity<String> healthLiquibase() {
    try (Connection connection = dataSource.getConnection();
        Liquibase liquibase =
            new Liquibase(
                "db/changelog/db.changelog-master.xml",
                new ClassLoaderResourceAccessor(),
                new JdbcConnection(connection))) {
      liquibase.validate();
      return new ResponseEntity<>("UP", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("LIQUIBASE DOWN", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
