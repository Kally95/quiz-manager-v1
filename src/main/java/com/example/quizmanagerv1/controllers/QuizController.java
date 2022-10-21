package com.example.quizmanagerv1.controllers;

import com.example.quizmanagerv1.model.Answers;
import com.example.quizmanagerv1.model.Quiz;
import com.example.quizmanagerv1.services.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class QuizController {
    private QuizService quizService;

    @GetMapping("/quizzes")
    public String getAllQuizzes(HttpSession session) {
        List<Quiz> allQuiz = quizService.getAll();
        session.setAttribute("qts", allQuiz);
        // Add currently logged in person to attribute object
        // do logic in template to see answers based on permissions.
        return "allQuizzes";
    }

    @GetMapping("/quizzes/{id}")
    public String getQuizById(@PathVariable Long id, HttpSession session) {
        Quiz selectedQuiz = quizService.getQuizById(id);
        session.setAttribute("quiz", selectedQuiz);
        return "selectedQuiz";
    }

    @GetMapping("/quizzes/create")
    public String getCreateQuizForm() {
        return "quizCreationForm";
    }

    @PostMapping("/quizzes/create")
    public String createQuiz(
            @RequestParam("question") String question,
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("option3") String option3,
            @RequestParam("option4") String option4,
            @RequestParam("answer") String answer,
            HttpSession session) {

        Quiz quiz = new Quiz();
        quiz.setUsername(session.getAttribute("user").toString());
        quiz.setQuestion(question);
        Answers answers = new Answers();
        answers.setOption1(option1);
        answers.setOption2(option2);
        answers.setOption3(option3);
        answers.setOption4(option4);
        answers.setAnswer(answer);

        List<Answers> ansList = new ArrayList<>();
        ansList.add(answers);
        quiz.setAnswers(ansList);

        quizService.save(quiz);

        List<Quiz> allQuiz = quizService.getAll();
        session.setAttribute("qts", allQuiz);

        return "allQuizzes";
    }

    @PostMapping("/validatequiz")
    public String validateQuiz(
            @RequestParam("quizid") String qid,
            @RequestParam("ans")
            String ans,
            RedirectAttributes attr) {

        Quiz qz = quizService.getQuizById(Long.parseLong(qid));

        if (qz.getAnswers().get(0).getAnswer().equals(ans))  {
            attr.addAttribute("success", true);
        }else {
            attr.addAttribute("fail", true);
        }

        return "redirect:/quizzes/"+qid;
    }

    @DeleteMapping("/quizzes/delete")
    public String deleteQuiz(@RequestParam("quizid") Long id, HttpSession session) {
        quizService.deleteQuizById(id);
        List<Quiz> allQuiz = quizService.getAll();
        session.setAttribute("qts", allQuiz);
           return "allQuizzes";
    }

    @GetMapping("/quizzes/update/{id}")
    public String updateQuiz(@PathVariable Long id, HttpSession session) {
        Quiz selectedQuiz = quizService.getQuizById(id);
        session.setAttribute("quiz", selectedQuiz);
        return "quizUpdateForm";
    }

    @PutMapping("/quizzes/update")
    public String updateQuiz(
            @RequestParam("quizid") String qid,
            @RequestParam("question") String question,
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("option3") String option3,
            @RequestParam("option4") String option4,
            @RequestParam("answer") String answer,
            HttpSession session) {

        Quiz updatedQuiz = quizService.getQuizById(Long.parseLong(qid));
        updatedQuiz.setQuestion(question);

        updatedQuiz.getAnswers().get(0).setOption1(option1);
        updatedQuiz.getAnswers().get(0).setOption2(option2);
        updatedQuiz.getAnswers().get(0).setOption3(option3);
        updatedQuiz.getAnswers().get(0).setOption4(option4);
        updatedQuiz.getAnswers().get(0).setAnswer(answer);

        quizService.save(updatedQuiz);

        List<Quiz> allQuiz = quizService.getAll();
        session.setAttribute("qts", allQuiz);

        return "allQuizzes";
    }

}
