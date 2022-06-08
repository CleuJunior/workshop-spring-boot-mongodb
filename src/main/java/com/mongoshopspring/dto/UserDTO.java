package com.mongoshopspring.dto;

import com.mongoshopspring.domain.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String email;

    public UserDTO() { }

    public UserDTO(User userObj) {
        this.id = userObj.getId();
        this.name = userObj.getName();
        this.email = userObj.getEmail();
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }
}
