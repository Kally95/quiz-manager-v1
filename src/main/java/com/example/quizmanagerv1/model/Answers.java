package com.example.quizmanagerv1.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Answers {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;
}
