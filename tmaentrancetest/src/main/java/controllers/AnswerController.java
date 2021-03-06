package controllers;

import models.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.AnswerService;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class AnswerController {
    protected final Logger logger2 = LogManager.getLogger();
    private AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @RequestMapping(value = "/answers", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Answer>> getAllAnswers() {
        return new ResponseEntity<ArrayList<Answer>>((ArrayList<Answer>) answerService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/answers/{id}", method = RequestMethod.GET)
    public Object getAnswerById(@PathVariable("id") int id) {
        return answerService.getAnswerById(id);
    }

    @RequestMapping(value ="/answers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") int id){
        Answer existingAnswer = this.answerService.getAnswerById(id);
        if(existingAnswer != null){
            this.answerService.delete(id);
        }
        return new ResponseEntity("Deleted Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/answers", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAnswer( @RequestBody Answer updateAnswer){
        Answer currentAnswer = this.answerService.getAnswerById(updateAnswer.getAnswerId());
        if(currentAnswer != null){
            this.answerService.update(updateAnswer);
        }
        return new ResponseEntity("Updated Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/answers", method = RequestMethod.POST)
    public ResponseEntity<?> addAnswer(@RequestBody Answer addAnswer){
        this.answerService.add(addAnswer);
        return new ResponseEntity("Added Successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
