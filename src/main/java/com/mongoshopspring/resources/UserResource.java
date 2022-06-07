package com.mongoshopspring.resources;

import com.mongoshopspring.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok()
                .body(List.of(

                new User("1", "Jhon", "jhondoe@gmail.com"),
                new User("2", "Jane", "janedoe@gmail.com"),
                new User("3", "Jasper", "jasperdoe@gmail.com")

        ));

    }
}
