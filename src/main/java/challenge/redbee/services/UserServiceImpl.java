package challenge.redbee.services;

import challenge.redbee.domain.User;
import challenge.redbee.exception.ResourceNotFoundException;
import challenge.redbee.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @Override
    public User getUserById(Long id) { return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario Inexistente."));
    }

    @Override
    public User saveUser(User user) { return userRepository.save(user); }

    @Override
    public void deleteById(Long id) { userRepository.deleteById(id); }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario Inexistente"));
        user.setBoards(userDetails.getBoards());
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public User saveOrReturn(User user) {
        User userSaved = getUserByEmail(user.getEmail());
        if(userSaved == null)
            return userRepository.save(user);
        else {
            return userSaved;
        }

    }
}
