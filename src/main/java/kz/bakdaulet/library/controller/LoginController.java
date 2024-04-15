package kz.bakdaulet.library.controller;

import jakarta.servlet.http.HttpServletResponse;
import kz.bakdaulet.library.model.User;
import kz.bakdaulet.library.security.AuthService;
import kz.bakdaulet.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/library")
public class LoginController {
    private final AuthService authService;
    private final BookService bookService;

    @Autowired
    public LoginController(AuthService authService, BookService bookService) {
        this.authService = authService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showMyLoginPage(Model model) {
        model.addAttribute("email", "");
        model.addAttribute("password", "");
        return "login";
    }

    @GetMapping("/register")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user) {
        authService.create(user);
        return "redirect:/library";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("email") String email, @ModelAttribute("password") String password, Model model) {
        System.out.println("email: " + email);
        User user = authService.login(email, password);
        if(user != null){
            model.addAttribute("user", user);
            if (user.getRole().equals("Admin")) {
                return "admin/main";
            }else {
                model.addAttribute("books", bookService.findAllBooks());
                model.addAttribute("searchResults", new ArrayList<>());
                model.addAttribute("userBooks", user.getBooks());
                model.addAttribute("query", "");
                return "user/main";
            }
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, Model model) {
        // Добавьте заголовок no-cache для предотвращения кэширования страницы
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        return showMyLoginPage(model);
    }
}
