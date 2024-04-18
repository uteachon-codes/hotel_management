package com.hotel.security;

import com.hotel.model.User;
import com.hotel.model.UserModel;

import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);
    public Optional<User> validEmailAndPassword(String email, String password);
}
