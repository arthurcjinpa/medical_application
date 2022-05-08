package medical_analytical_prescription.service;

import medical_analytical_prescription.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> showAllUsers();

    void addUser(User user);

    void deleteUsers();



}
