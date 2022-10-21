package com.example.quizmanagerv1.controllers;

import com.example.quizmanagerv1.model.LoginForm;
import com.example.quizmanagerv1.model.User;
import com.example.quizmanagerv1.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Controller
public class LoginController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        session.removeAttribute("userperm");
        return "login.html";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model, HttpSession session) {
        {
            String username = loginForm.getUsername();
            String password = loginForm.getPassword();

            User dbUser = userService.getUserByUserName(username);

            if (dbUser != null && !password.isEmpty() && passwordEncoder.matches(loginForm.getPassword(), dbUser.getPassword())) {
                session.setAttribute("user", dbUser.getUsername());
                session.setAttribute("userperm", dbUser.getPermission().toString());
                return "redirect:/quizzes";
            } else {
                model.addAttribute("invalidCredentials", true);
                return "login";
            }
        }
    }
}
