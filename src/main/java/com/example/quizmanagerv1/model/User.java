package com.example.quizmanagerv1.model;

import com.example.quizmanagerv1.security.Permission;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "permission_type", nullable = false)
    private Permission permission;
    public User(String username, String email, String password, Permission permission) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.permission = permission;
    }

}
