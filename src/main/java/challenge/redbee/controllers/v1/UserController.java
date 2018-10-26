package challenge.redbee.controllers.v1;

import challenge.redbee.domain.User;
import challenge.redbee.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
@Api(value = "User", description = "REST API for User.", tags = { "User" })
public class UserController {

    public static final String BASE_URL = "/users";

    private UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @ApiOperation(value="Get Users", tags = { "User" })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() { return userService.getAllUsers(); }

    @ApiOperation(value="Get User By Id", tags = { "User" })
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id) { return userService.getUserById(id); }

    @ApiOperation(value="Create User", tags = { "User" })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody User user) { return userService.saveOrReturn(user); }

    @ApiOperation(value="Delete User By Id", tags = { "User" })
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) { userService.deleteById(id); }

    @ApiOperation(value="Update User", tags = { "User" })
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id, @RequestBody User user) { return userService.updateUser(id,user); }

}
