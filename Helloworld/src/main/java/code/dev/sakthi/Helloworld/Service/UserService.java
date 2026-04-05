package code.dev.sakthi.Helloworld.Service;

import code.dev.sakthi.Helloworld.models.Todo;
import code.dev.sakthi.Helloworld.models.User;
import code.dev.sakthi.Helloworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    UserRepository userRepository;


    // createUser
    public User createUser(User user){
        return userRepository.save(user);
    }

    // get particular user
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Id not exists"));
    }
}
