package com.shopmart.userservice.controller;

import com.netflix.discovery.converters.Auto;
import com.shopmart.userservice.model.User;
import com.shopmart.userservice.model.UserDTO;
import com.shopmart.userservice.source.PasswordValidator;
import com.shopmart.userservice.source.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PasswordValidator passwordValidator;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        if(!user.getPassPlainText().equals(user.getPassPlainTextConfirm())){
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.findByEmail(user.getEmail()) != null){
            String message = "User with email " + user.getEmail() + " already exists";
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }
        if(userRepository.findByPhone(user.getPhone()) != null){
            String message = "User with phone " + user.getPhone() + " already exists";
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }

        if(!passwordValidator.validate(user.getPassPlainText())){
            String message = "Password must contain at least one uppercase letter, one lowercase letter, one digit and one special character";
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassPlainText()));
        System.out.println(user);
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDto = users.stream()
                                .map(UserDTO::new).toList();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
