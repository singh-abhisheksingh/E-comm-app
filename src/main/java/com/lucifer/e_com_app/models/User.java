package com.lucifer.e_com_app.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private String email;

    @Column(unique = true)
    private long mobile;
    private String name;
    private String password;
    private String role;

}
