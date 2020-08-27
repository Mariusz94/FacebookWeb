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
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lyszczarzmariusz.FacebookWeb.models.UserModel;
import pl.lyszczarzmariusz.FacebookWeb.models.forms.RegisterForm;
import pl.lyszczarzmariusz.FacebookWeb.models.repositories.UserRepository;
import pl.lyszczarzmariusz.FacebookWeb.models.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegisterController {

    final
    UserRepository userRepository;

    final
    UserService userService;

    Logger logger;

    @Autowired
    public RegisterController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
        logger = LoggerFactory.getLogger(RegisterController.class);
    }

    @GetMapping(path = "/register")
    public String registerPage(Model model) {
        logger.info("Sending empty RegisterForm");
        model.addAttribute("registerForm", new RegisterForm());
        logger.info("Display template \"registerPage\" in browser");
        return "registerPage";
    }

    //todo logger
    @PostMapping("/register")
    public String registerPageAnswer(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult bindingResult, Model model) {
        userService.showErrors(bindingResult, model);

        logger.info("Search user in database");
        Optional<UserModel> userModel = userRepository.findByEmail(registerForm.getEmail());

        if (userModel.isPresent()) {
            logger.info("Attempting to use an email address found in the database");

            model.addAttribute("emailFieldBusyError", "This email is used by another user");
            logger.info("Sending RegisterForm");
            model.addAttribute("registerForm", registerForm);
            logger.info("Display template \"registerPage\" in browser");
            return "registerPage";
        }

        if (!bindingResult.hasErrors() && registerForm.getPassword().equals(registerForm.getRepeatedPassword())) {
            logger.info("Try save user to database");
            userRepository.save(UserModel.createUser(registerForm));
            logger.info("Saved user to database");
            userService.setUser(UserModel.createUser(registerForm));
            logger.info("Redirect to \"/\"");
            return "redirect:/";
        }

        logger.info("Passwords are different");
        model.addAttribute("repeatedPasswordFieldSameError", "Passwords are different");
        logger.info("Clear password");
        registerForm.setPassword("");
        registerForm.setRepeatedPassword("");
        logger.info("Sending RegisterForm");
        model.addAttribute("registerForm", registerForm);
        logger.info("Display template \"registerPage\" in browser");
        return "registerPage";
    }

}
