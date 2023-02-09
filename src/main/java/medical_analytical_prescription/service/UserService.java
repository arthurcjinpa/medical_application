package medical_analytical_prescription.service;

import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  List<User> getAllUsers();

  User getUserById(Long id);

  User getUserByEmail(String email);

  User addUser(User user);

  void deleteUserByUserId(Long userId);

  void updateUser(User user);

  User checkUsersEmailUniqueness(Application application);
}
