package challenge.redbee.services;

import challenge.redbee.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String mail);

    User saveUser(User user);

    User saveOrReturn(User user);

    User updateUser(Long id, User user);

    void deleteById(Long id);

}
