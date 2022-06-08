package com.mongoshopspring.resources;

import com.mongoshopspring.domain.User;
import com.mongoshopspring.dto.UserDTO;
import com.mongoshopspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = this.userService.findAll();

        List<UserDTO> userListDto = users.stream()
                .map(UserDTO::new)
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok()
                .body(userListDto);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable String id) {
        User userId = this.userService.findById(id);

        return ResponseEntity.ok()
                .body(new UserDTO(userId));
    }
}
