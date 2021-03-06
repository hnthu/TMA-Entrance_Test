package controllers;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    protected final Logger logger2 = LogManager.getLogger();
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Object getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Object getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value ="/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        User existingUser = this.userService.getUserById(id);
        if(existingUser != null){
            this.userService.delete(id);
        }
        return new ResponseEntity("Delete Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User updateUser){
        User currentUser = this.userService.getUserById(updateUser.getId());
        if(currentUser != null){
            this.userService.update(updateUser);
        }
        return new ResponseEntity("Updated successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User addUser){
        this.userService.add(addUser);
        return new ResponseEntity("Added successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
