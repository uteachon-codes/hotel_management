package com.hotel.security;

import com.hotel.model.User;
import com.hotel.model.UserModel;
import com.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("ROLE_"+userModel.getRole());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> validEmailAndPassword(String useremail, String password) {
        return getUserByUsername(useremail)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }


    public Optional<User> getUserByUsername(String useremail) {
        return userRepository.findByEmail(useremail);
    }

}
