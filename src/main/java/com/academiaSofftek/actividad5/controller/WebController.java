package com.academiaSofftek.actividad5.controller;

import com.academiaSofftek.actividad5.dto.user.LoginUserDTO;
import com.academiaSofftek.actividad5.dto.user.RegisterUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class handling web-related operations, such as displaying login, registration,
 * and to-do list forms.
 *
 * @Controller Indicates that this class serves as a Spring MVC controller.
 * @RequestMapping("/web") Base mapping for all endpoints in this controller.
 */
@Controller
@RequestMapping("/web")
public class WebController {

    /**
     * Displays the login form.
     *
     * @param model The model to which attributes can be added for rendering in the view.
     * @return The logical view name for the login form template.
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginUserDto", new LoginUserDTO());
        return "login"; // This translates to "src/main/resources/templates/login.html"
    }

    /**
     * Displays the registration form.
     *
     * @param model The model to which attributes can be added for rendering in the view.
     * @return The logical view name for the registration form template.
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDTO());
        return "register"; // This translates to "src/main/resources/templates/register.html"
    }

    /**
     * Displays the to-do list page.
     *
     * @return The logical view name for the to-do list template.
     */
    @GetMapping("/toDo")
    public String showToDoForm() {
        return "toDo"; // This translates to "src/main/resources/templates/toDo.html"
    }
}

