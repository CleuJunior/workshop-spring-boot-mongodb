package com.mongoshopspring.services;

import com.mongoshopspring.domain.User;
import com.mongoshopspring.dto.UserDTO;
import com.mongoshopspring.repositories.IUserRepository;
import com.mongoshopspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) { this.userRepository = userRepository; }

    public List<User> findAll() { return this.userRepository.findAll(); }

    public User findById(String id) {
        return this.userRepository.findById(id).
            orElseThrow(() -> new ObjectNotFoundException("User ID not Found"));

    }

    public User insertUser(User userObj) { return this.userRepository.insert(userObj); }

    public User fromDTO(UserDTO dtoObj) { return new User(dtoObj.getId(), dtoObj.getName(), dtoObj.getEmail()); }
}
