package medical_analytical_prescription.controller;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUsers(@PathVariable Long userId) {
        userService.deleteUserByUserId(userId);
    }
}