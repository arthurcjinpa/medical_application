package medical_analytical_prescription.controller;

import lombok.RequiredArgsConstructor;
import medical_analytical_prescription.entity.User;
import medical_analytical_prescription.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users/all")
    public List<User> allUsers() {
        return userService.showAllUsers();
    }

    @PostMapping("user/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping("users/delete")
    public void deleteUsers() {
        userService.deleteUsers();
    }

}
