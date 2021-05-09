package com.support.tickets.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User fetchUserById(Integer id){
        return userRepository.getUserById(id);
    }
}
