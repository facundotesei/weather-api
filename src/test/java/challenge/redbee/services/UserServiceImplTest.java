package challenge.redbee.services;

import challenge.redbee.domain.User;
import challenge.redbee.exception.ResourceNotFoundException;
import challenge.redbee.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "User";

    @Mock
    UserRepository userRepository;

    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void getUserById() throws Exception {

        //given
        User user = new User();
        user.setId(ID);
        user.setName(NAME);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //when
        User user1 = userService.getUserById(ID);

        //then
        assertEquals(ID, user1.getId());
        assertEquals(NAME, user1.getName());

    }

    @Test
    public  void getUsers() throws Exception {

        //given
        List<User> users = Arrays.asList(new User(), new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        //when
        List<User> userList = userService.getAllUsers();

        //then
        assertEquals(3, userList.size());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getUserByIdNotFound() throws Exception {

        //given
        given(userRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        User user = userService.getUserById(1L);

        //then
        then(userRepository).should(times(1)).findById(anyLong());

    }

    @Test
    public void deleteUserById() throws Exception {

        //when
        userService.deleteById(1L);

        //then
        then(userRepository).should().deleteById(anyLong());
    }
}
