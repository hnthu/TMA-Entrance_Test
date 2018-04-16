package controllers;

import models.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.AnswerService;

@RestController
public class AnswerController {
    protected final Logger logger2 = LogManager.getLogger();
    private AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @RequestMapping(value = "/getallanswers", method = RequestMethod.GET)
    public Object getAllAnswers() {
        logger2.info("is running get all Answer in the database.");
        logger2.debug("is running get all Answer in the database.");
        logger2.error("is running get all Answer in the database.");
        return answerService.getAll();
    }

    @RequestMapping(value = "/getanswerbyid/{id}", method = RequestMethod.GET)
    public Object getAnswerById(@PathVariable("id") int id) {
        return answerService.getAnswerById(id);
    }

    @RequestMapping(value ="/deleteanswer/{id}", method = RequestMethod.DELETE)
    public String deleteAnswer(@PathVariable("id") int id){
        Answer existingCategory = this.answerService.getAnswerById(id);
        if(existingCategory != null){
            this.answerService.delete(id);
        }
        return "Deleted Successfully";
    }

    @RequestMapping(value = "/updateanswer/{id}", method = RequestMethod.PUT)
    public Object updateAnswer(@PathVariable("id") int id,  @RequestBody Answer updateAnswer){
        Answer currentAnswer = this.answerService.getAnswerById(id);
        if(currentAnswer != null){
            this.answerService.update(updateAnswer);
        }
        return "Updated successfully";
    }

    @RequestMapping(value = "/addanswer", method = RequestMethod.POST)
    public String addAnswer(@RequestBody Answer addAnswer){
        this.answerService.add(addAnswer);
        return "Added successfully";
    }
}
