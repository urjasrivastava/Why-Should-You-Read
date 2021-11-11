package com.sdp.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Document(indexName = "blog_user")
@Data
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String role;

    private String description;

    public User(String id, String username, String password, String role, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.description = description;
    }

    public User() {
        this.id = null;
        this.username = null;
        this.password = null;
        this.role = null;
        this.description = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}