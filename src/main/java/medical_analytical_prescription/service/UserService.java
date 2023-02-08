package medical_analytical_prescription.service;

import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

  List<User> getAllUsers();

  User getUserById(Long id);

  Optional<User> getUserByEmail(String email);

  User addUser(User user);

  void deleteUserByUserId(Long userId);

  void updateUser(User user);

  User checkUsersEmailUniqueness(Application application);
}
