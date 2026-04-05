package code.dev.sakthi.Helloworld.controller;

import code.dev.sakthi.Helloworld.Service.UserService;
import code.dev.sakthi.Helloworld.models.User;
import code.dev.sakthi.Helloworld.repository.UserRepository;
import code.dev.sakthi.Helloworld.utils.Jwtutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final Jwtutil jwtutil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));

        if(userRepository.findByEmail(email).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists !!");
        }

        userService.createUser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("Successfully registered!" , HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User not exists !!");
        }

        User user = userOptional.get();

        if(!passwordEncoder.matches(password,user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
        }

        String token = jwtutil.generateToken(email);

        return  ResponseEntity.ok(Map.of("token",token));

    }


}
