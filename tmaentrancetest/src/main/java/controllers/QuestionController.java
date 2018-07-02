package controllers;

import models.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.QuestionService;

@RestController
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class QuestionController {
    protected final Logger logger2 = LogManager.getLogger();
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/getallquestions", method = RequestMethod.GET)
    public Object getAllQuestions() {
        logger2.info("is running get all question in the database.");
        logger2.debug("is running get all question in the database.");
        logger2.error("is running get all question in the database.");
        return questionService.getAll();
    }

    @RequestMapping(value = "/getQuestionsByProgrammingLanguage/{technical}", method = RequestMethod.GET)
    public Object getQuestionsByProgrammingLanguage(@PathVariable("technical") String technical) {
        return questionService.getQuestionsByProgrammingLanguage(technical);
    }

    @RequestMapping(value = "/getquestionbyid/{id}", method = RequestMethod.GET)
    public Object getQuestionById(@PathVariable("id") int id) {
        return questionService.getQuestionById(id);
    }

    @RequestMapping(value ="/deletequestion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") int id){
        Question existingQuestion = this.questionService.getQuestionById(id);
        if(existingQuestion != null){
            this.questionService.delete(id);
        }
        return new ResponseEntity("Deleted Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/updatequestion/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuestion(@PathVariable("id") int id,  @RequestBody Question updateUser){
        Question currentQuestion = this.questionService.getQuestionById(id);
        if(currentQuestion != null){
            this.questionService.update(updateUser);
        }
        return new ResponseEntity("Updated Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
    public ResponseEntity<?> addQuestion(@RequestBody Question addQuestion){
        this.questionService.add(addQuestion);
        return new ResponseEntity("Added Successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
