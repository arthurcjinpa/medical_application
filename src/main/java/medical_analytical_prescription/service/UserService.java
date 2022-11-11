package medical_analytical_prescription.service;

import medical_analytical_prescription.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void deleteUserByUserId(Long userId);
}