package com.ankita.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ankita.ecommerce.entity.User;
import com.ankita.ecommerce.repository.UserRepository;



@Service
public class UserService {
     @Autowired
private  UserRepository userRepository ;

    
   @Autowired
private PasswordEncoder passwordEncoder;

public User register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
}
   
}
