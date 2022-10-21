package com.example.quizmanagerv1.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String question;

    @Column(name = "user_name")
    private String username;

    @OneToMany
    private List<Answers> answers;

    private String created_by;

}
