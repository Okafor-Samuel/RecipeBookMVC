package com.example.bookrecipe.Model.Service;

import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User hashPassword(User user) {
        // Hash the user's password
        String hashedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(hashedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        // Check if the provided password matches the stored password
        return BCrypt.checkpw(password, user.get().getUserPassword());
    }

    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}


