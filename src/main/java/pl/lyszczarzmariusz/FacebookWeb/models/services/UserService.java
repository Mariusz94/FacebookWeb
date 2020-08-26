package pl.lyszczarzmariusz.FacebookWeb.models.services;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lyszczarzmariusz.FacebookWeb.controllers.MainController;
import pl.lyszczarzmariusz.FacebookWeb.models.UserModel;

@Service
@Data
public class UserService {
    UserModel userModel;
    boolean isLogged = false;

    Logger logger;

    @Autowired
    public UserService() {
        logger = LoggerFactory.getLogger(UserService.class);
    }

    public void setUser(UserModel userModel){
        logger.info("Set user in UserService");
        this.userModel = userModel;
        logger.info("Logged user");
        isLogged = true;
    }
}
