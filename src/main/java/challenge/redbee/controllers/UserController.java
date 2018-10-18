package challenge.redbee.controllers;

import challenge.redbee.domain.User;
import challenge.redbee.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/users";

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getUsers(){ return userService.getAllUsers(); }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id){ return userService.getUserById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody User user){ return userService.saveUser(user); }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){ userService.deleteById(id); }

}
