package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.exception.UserNotFoundException;
import medical_analytical_prescription.repository.UserRepository;
import medical_analytical_prescription.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public User getUserByEmail(String email) {
    return userRepository
        .getUserByEmail(email)
        .orElseThrow(
            () -> {
              throw new UserNotFoundException("User with email " + email + " not found.");
            });
  }

  @Override
  public User addUser(User user) {

    if (isUserAlreadyRegistered(user.getEmail())) {
      throw new UserNotFoundException("User with email " + user.getEmail() + " not found.");
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
    if (isUserAlreadyRegistered(application.getApplicant().getEmail())) {
      return getUserByEmail(application.getApplicant().getEmail());
    } else {
      return registerUserAndSetApplication(application);
    }
  }

  private User registerUserAndSetApplication(Application application) {
    User registeredUser = addUser(application.getApplicant());
    application.setApplicant(registeredUser);
    return registeredUser;
  }

  private boolean isUserAlreadyRegistered(String email) {
    return userRepository.existsUserByEmail(email);
  }
}
