package com.mongoshopspring.resources;

import com.mongoshopspring.domain.User;
import com.mongoshopspring.dto.UserDTO;
import com.mongoshopspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<UserDTO> insertNewUser(@RequestBody UserDTO userDTO) {
        User userObj = this.userService.fromDTO(userDTO);
        userObj = this.userService.insertUser(userObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userObj.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        this.userService.deleteUser(id);

        return ResponseEntity.noContent()
                .build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User userObj = this.userService.fromDTO(userDTO);

        userObj.setId(id);
        userObj = this.userService.userUpdate(userObj);

        return ResponseEntity.notFound()
                .build();
    }

}
