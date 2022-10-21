package com.example.quizmanagerv1.services;

import com.example.quizmanagerv1.model.Quiz;
import com.example.quizmanagerv1.repositories.AnswerRepository;
import com.example.quizmanagerv1.repositories.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.quizmanagerv1.utils.Capitalise.capitaliseData;

@Service
@AllArgsConstructor
public class QuizService {
    private QuizRepository quizRepository;
    private AnswerRepository answerRepository;

    public Quiz getQuizById(Long id) {
        return quizRepository.getById(id);
    }
    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    public void save(Quiz quiz) {
        quiz.setQuestion(capitaliseData(quiz.getQuestion()));
        answerRepository.save(quiz.getAnswers().get(0));
        quizRepository.save(quiz);
    }

    public void deleteQuizById(Long qid) {
        quizRepository.deleteById(qid);
    }

}
