package challenge.redbee.controllers.v1;

import challenge.redbee.domain.User;
import challenge.redbee.exception.ResourceNotFoundException;
import challenge.redbee.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static challenge.redbee.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {UserController.class})
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    User user_1;
    User user_2;

    @Before
    public void setUp() throws Exception {
        user_1 = new User("User 1", "user1@gmail.com");
        user_2 = new User("User 2", "user2@gmail.com");
    }

    @Test
    public void getUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user_1, user_2));

        given(userService.getAllUsers()).willReturn(users);

        mockMvc.perform(get(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.users", hasSize(2)));
    }

    @Test
    public void getUserById() throws Exception {

        given(userService.getUserById(anyLong())).willReturn(user_1);

        mockMvc.perform(get(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(user_1.getName())));
    }

    @Test
    public void createNewUser() throws Exception {

        given(userService.saveUser(user_1)).willReturn(user_1);

        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(user_1.getName())));
    }

    @Test
    public void updateUser() throws Exception {

        given(userService.updateUser(anyLong(), any(User.class))).willReturn(user_1);

        mockMvc.perform(put(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(user_1.getName())));
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete(UserController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(userService).should().deleteById(anyLong());

    }

    @Test
    public void GetUserByIdNotFound() throws Exception {

        when(userService.getUserById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
