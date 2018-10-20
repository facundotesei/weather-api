package challenge.redbee.services;

import challenge.redbee.domain.User;

public interface UserService {

    Iterable<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User user);

    User updateUser(Long id, User user);

    void deleteById(Long id);

}
