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
import pl.lyszczarzmariusz.FacebookWeb.models.forms.LoginForm;
import pl.lyszczarzmariusz.FacebookWeb.models.forms.RegisterForm;
import pl.lyszczarzmariusz.FacebookWeb.models.repositories.UserRepository;
import pl.lyszczarzmariusz.FacebookWeb.models.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class MainController {

    final
    UserRepository userRepository;

    final
    UserService userService;

    Logger logger;

    @Autowired
    public MainController(UserRepository userRepository,UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
        logger = LoggerFactory.getLogger(MainController.class);
    }

    @GetMapping(path="/")
    public String mainPage () {
        if (userService.isLogged()){
            logger.info("Display template \"dashboardPage\" in browser");
            return "dashboardPage";
        }
        logger.info("Redirect to \"/login\"");
        return "redirect:/login";
    }

}
