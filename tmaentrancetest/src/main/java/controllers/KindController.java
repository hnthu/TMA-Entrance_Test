package controllers;

import models.Kind;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.KindService;

@RestController
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class KindController {
    protected final Logger logger2 = LogManager.getLogger();
    private KindService kindService;

    @Autowired
    public void setCatgoryService(KindService questionTypeService) {
        this.kindService = questionTypeService;
    }

    @RequestMapping(value = "/getallkinds", method = RequestMethod.GET)
    public Object getAllQuestionTypes() {
        logger2.info("is running get all user in the database.");
        logger2.debug("is running get all user in the database.");
        logger2.error("is running get all user in the database.");
        return kindService.getAll();
    }

    @RequestMapping(value = "/getkindbyid/{id}", method = RequestMethod.GET)
    public Object getQuestionTypeById(@PathVariable("id") int id) {
        return kindService.getKindById(id);
    }

    @RequestMapping(value ="/deletekind/{id}", method = RequestMethod.DELETE)
    public String deleteQuestionType(@PathVariable("id") int id){
        Kind existingQuestionType = this.kindService.getKindById(id);
        if(existingQuestionType != null){
            this.kindService.delete(id);
        }
        return "Deleted Successfully";
    }

    @RequestMapping(value = "/updatekind/{id}", method = RequestMethod.PUT)
    public Object updateQuestionType(@PathVariable("id") int id,  @RequestBody Kind updateUser){
        Kind currentQuestionType = this.kindService.getKindById(id);
        if(currentQuestionType != null){
            this.kindService.update(updateUser);
        }
        return "Updated successfully";
    }

    @RequestMapping(value = "/addkind", method = RequestMethod.POST)
    public String addQuestionType(@RequestBody Kind addKind){
        this.kindService.add(addKind);
        return "Added successfully";
    }
}
