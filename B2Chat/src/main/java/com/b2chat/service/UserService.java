package com.b2chat.service;

import com.b2chat.entity.User;
import com.b2chat.entity.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    public User retrieveById(Long id) {
        try {
            Optional<User>user = userRepository.findById(id);
            return user.get();
        }
         catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado");
        }
    }

    public void deleteUser(Long id){
        try {
            retrieveById(id);
            userRepository.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public User update( User user){
        try {
            retrieveById(user.getId());
            return userRepository.save(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
