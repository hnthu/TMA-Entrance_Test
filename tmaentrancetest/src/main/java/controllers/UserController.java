package controllers;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import services.UserService;

import java.util.Set;

@RestController
public class UserController {
    protected final Logger logger2 = LogManager.getLogger();
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Set<User> user() {
        logger2.info("is running get all user in the database.");
        logger2.debug("is running get all user in the database.");
        logger2.error("is running get all user in the database.");
        return userService.getAll();
    }

}
