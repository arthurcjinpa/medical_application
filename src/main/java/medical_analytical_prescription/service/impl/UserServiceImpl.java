package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.exception.UserAlreadyRegisteredException;
import medical_analytical_prescription.exception.UserNotFoundException;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new UserNotFoundException("User with id " + id + " not found.");
            });
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return userRepository.getUserByEmail(email);
  }

  @Override
  public User addUser(User user) {

    Optional<User> userByEmail = getUserByEmail(user.getEmail());

    if (userByEmail.isPresent()) {
      throw new UserAlreadyRegisteredException(
          "User with email " + user.getEmail() + " is already created");
    }

    return userRepository.save(user);
  }

  @Override
  public void deleteUserByUserId(Long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public void updateUser(User user) {
    userRepository.save(user);
  }

  public User checkUsersEmailUniqueness(Application application) {
    return getUserByEmail(application.getApplicant().getEmail())
        .orElseGet(() -> registerUserAndSetApplication(application));
  }

  private User registerUserAndSetApplication(Application application) {
    User registeredUser = addUser(application.getApplicant());
    application.setApplicant(registeredUser);
    return registeredUser;
  }
}
