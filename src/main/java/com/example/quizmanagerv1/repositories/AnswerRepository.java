package com.example.quizmanagerv1.repositories;

import com.example.quizmanagerv1.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
}
