package medical_analytical_prescription;

import com.netflix.discovery.EurekaClient;
import medical_analytical_prescription.feign.MockPrescriptionClient;
import medical_analytical_prescription.mapper.ApplicationMapper;
import medical_analytical_prescription.mapper.UserMapper;
import medical_analytical_prescription.repository.ApplicationRepository;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.ApplicationService;
import medical_analytical_prescription.service.UserService;
import medical_analytical_prescription.service.impl.ApplicationServiceImpl;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class BaseTest {

  @Autowired protected ApplicationService applicationService;
  @Autowired protected ApplicationServiceImpl applicationServiceImpl;
  @Autowired protected UserService userService;
  @Autowired protected UserRepository userRepository;
  @Autowired protected ApplicationRepository applicationRepository;
  @Autowired protected ApplicationMapper applicationMapper;
  @Autowired protected UserMapper userMapper;
  @Autowired protected MockPrescriptionClient mockPrescriptionClient;
  @Autowired protected EurekaClient eurekaClient;
}
