package controllers;

import models.QuestionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.QuestionTypeService;

@RestController
public class QuestionTypeController {
    protected final Logger logger2 = LogManager.getLogger();
    private QuestionTypeService questionTypeService;

    @Autowired
    public void setCatgoryService(QuestionTypeService questionTypeService) {
        this.questionTypeService = questionTypeService;
    }

    @RequestMapping(value = "/getallquestiontypes", method = RequestMethod.GET)
    public Object getAllQuestionTypes() {
        logger2.info("is running get all user in the database.");
        logger2.debug("is running get all user in the database.");
        logger2.error("is running get all user in the database.");
        return questionTypeService.getAll();
    }

    @RequestMapping(value = "/getquestiontypebyid/{id}", method = RequestMethod.GET)
    public Object getQuestionTypeById(@PathVariable("id") int id) {
        return questionTypeService.getQuestionTypeById(id);
    }

    @RequestMapping(value ="/deletequestiontype/{id}", method = RequestMethod.DELETE)
    public String deleteQuestionType(@PathVariable("id") int id){
        QuestionType existingQuestionType = this.questionTypeService.getQuestionTypeById(id);
        if(existingQuestionType != null){
            this.questionTypeService.delete(id);
        }
        return "Deleted Successfully";
    }

    @RequestMapping(value = "/updatequestiontype/{id}", method = RequestMethod.PUT)
    public Object updateQuestionType(@PathVariable("id") int id,  @RequestBody QuestionType updateUser){
        QuestionType currentQuestionType = this.questionTypeService.getQuestionTypeById(id);
        if(currentQuestionType != null){
            this.questionTypeService.update(updateUser);
        }
        return "Updated successfully";
    }

    @RequestMapping(value = "/addquestiontype", method = RequestMethod.POST)
    public String addQuestionType(@RequestBody QuestionType addQuestionType){
        this.questionTypeService.add(addQuestionType);
        return "Added successfully";
    }
}
