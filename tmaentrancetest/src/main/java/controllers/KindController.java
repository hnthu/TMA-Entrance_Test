package controllers;

import models.Kind;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.KindService;

@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class KindController {
    protected final Logger logger2 = LogManager.getLogger();
    private KindService kindService;

    @Autowired
    public void setCatgoryService(KindService questionTypeService) {
        this.kindService = questionTypeService;
    }

    @RequestMapping(value = "/kinds", method = RequestMethod.GET)
    public Object getAllQuestionTypes() {
        return kindService.getAll();
    }

    @RequestMapping(value = "/kinds/{id}", method = RequestMethod.GET)
    public Object getQuestionTypeById(@PathVariable("id") int id) {
        return kindService.getKindById(id);
    }

    @RequestMapping(value ="/kinds/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestionType(@PathVariable("id") int id){
        Kind existingQuestionType = this.kindService.getKindById(id);
        if(existingQuestionType != null){
            this.kindService.delete(id);
        }
        return new ResponseEntity("Deleted Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/kinds", method = RequestMethod.PUT)
    public  ResponseEntity<?> updateQuestionType(@RequestBody Kind updateKind){
        Kind currentQuestionType = this.kindService.getKindById(updateKind.getKindId());
        if(currentQuestionType != null){
            this.kindService.update(updateKind);
        }
        return new ResponseEntity("Updated Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/kinds", method = RequestMethod.POST)
    public  ResponseEntity<?> addQuestionType(@RequestBody Kind addKind){
        this.kindService.add(addKind);
        return new ResponseEntity("Added Successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
