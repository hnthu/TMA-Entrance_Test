package controllers;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import java.util.List;

@RestController
public class UserController {
    protected final Logger logger2 = LogManager.getLogger();
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET)
    public Object getAllUsers() {
        logger2.info("is running get all user in the database.");
        logger2.debug("is running get all user in the database.");
        logger2.error("is running get all user in the database.");
        return userService.getAll();
    }

    @RequestMapping(value = "/getuserbyid/{id}", method = RequestMethod.GET)
    public Object getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value ="/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") int id){
        User existingUser = this.userService.getUserById(id);
        if(existingUser != null){
            this.userService.delete(id);
        }
        return "Deleted Successfully";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Object updateUser(@PathVariable("id") int id,  @RequestBody User updateUser){
        User currentUser = this.userService.getUserById(id);
        if(currentUser != null){
            this.userService.update(updateUser);
        }
        return "Updated successfully";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody User addUser){
        this.userService.add(addUser);
        return "Added successfully";
    }
}
