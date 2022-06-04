package com.bnz.shared.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private String firstName;
    private String lastName;
    private int role = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRole() {
        return role;
    }

    public void addNewRole(int newRole) {
        if((newRole & this.role) != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You already have this role.");
        }
        this.role += newRole;
    }
    public void deleteRole(int newRole) {
        if((newRole & this.role) == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't remove a role that you don't have");
        }
        this.role -= newRole;
    }
    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
