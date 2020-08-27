package pl.lyszczarzmariusz.FacebookWeb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lyszczarzmariusz.FacebookWeb.models.UserModel;
import pl.lyszczarzmariusz.FacebookWeb.models.forms.LoginForm;
import pl.lyszczarzmariusz.FacebookWeb.models.repositories.UserRepository;
import pl.lyszczarzmariusz.FacebookWeb.models.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {

    final
    UserRepository userRepository;

    final
    UserService userService;

    Logger logger;

    @Autowired
    public LoginController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
        logger = LoggerFactory.getLogger(LoginController.class);
    }

    @GetMapping(path = "/login")
    public String loginPage(Model model) {
        logger.info("Sending empty LoginForm");
        model.addAttribute("loginForm", new LoginForm());
        logger.info("Display template \"loginPage\" in browser");
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginPageAnswer(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
        userService.showErrors(bindingResult, model);

        logger.info("Search user in database");
        Optional<UserModel> userModel = userRepository.findByEmail(loginForm.getEmail());

        if (userModel.isPresent() && userModel.get().getPassword().equals(loginForm.getPassword())) {
            userService.setUser(userModel.get());
            logger.info("Redirect to \"/\"");
            return "redirect:/";
        }

        logger.info("Wrong data");
        model.addAttribute("errorLogin", "Incorrect data");
        logger.info("Clear password");
        loginForm.setPassword("");
        logger.info("Sending LoginForm");
        model.addAttribute("loginForm", loginForm);
        logger.info("Display template \"loginPage\" in browser");
        return "loginPage";
    }

}
