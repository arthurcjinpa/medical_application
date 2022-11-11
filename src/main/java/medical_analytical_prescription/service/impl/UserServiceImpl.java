package medical_analytical_prescription.service.impl;

import lombok.RequiredArgsConstructor;
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
        return userRepository.findById(id)
                .orElseThrow(
                        () -> {
                            throw new UserNotFoundException("User with id " + id + " not found.");
                        });
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserByUserId(Long userId) {
        userRepository.deleteById(userId);
    }
}