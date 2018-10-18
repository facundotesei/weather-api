package challenge.redbee.services;

import challenge.redbee.domain.User;
import challenge.redbee.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getAllUsers() { return userRepository.findAll(); }

    @Override
    public User getUserById(Long id) { return userRepository.findById(id).orElse(null); }

    @Override
    public User saveUser(User user) { return userRepository.save(user); }

    @Override
    public void deleteById(Long id) { userRepository.deleteById(id); }
}
