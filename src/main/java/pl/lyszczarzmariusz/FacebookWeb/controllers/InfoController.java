package pl.lyszczarzmariusz.FacebookWeb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.lyszczarzmariusz.FacebookWeb.models.repositories.UserRepository;

@Controller
public class InfoController {

    Logger logger;

    final
    UserRepository userRepository;

    @Autowired
    public InfoController(UserRepository userRepository) {
        logger = LoggerFactory.getLogger(InfoController.class);
        this.userRepository = userRepository;
    }

    @GetMapping(path="/info")
    public String isServerWorks () {
        logger.info("info");
        logger.error("error");
        logger.warn("warn");
        logger.debug("debug");
        logger.trace("trace");
        logger.info("infoPage");
        return "infoPage";
    }

    @GetMapping(path = "/getAllUsers")
    public String printAllUser(Model model){
        logger.info("Getting users from database");
        model.addAttribute("users",userRepository.findAll());
        logger.info("Display template \"allUsers\" in browser");
        return "allUsers";
    }

}
