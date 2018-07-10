package controllers;

import models.Interview;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.InterviewService;

@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class InterviewController {
    protected final Logger logger2 = LogManager.getLogger();
    private InterviewService interviewService;

    @Autowired
    public void setInterviewService(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @RequestMapping(value = "/interviews", method = RequestMethod.GET)
    public Object getAllInterviews() {
        return interviewService.getAll();
    }

    @RequestMapping(value = "/interviews/{id}", method = RequestMethod.GET)
    public Object getInterviewById(@PathVariable("id") int id) {
        return interviewService.getInterviewById(id);
    }

    @RequestMapping(value ="/interviews/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteInterview(@PathVariable("id") int id){
        Interview existingInterview = this.interviewService.getInterviewById(id);
        if(existingInterview != null){
            this.interviewService.delete(id);
        }
        return new ResponseEntity("Deleted Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/interviews", method = RequestMethod.PUT)
    public ResponseEntity<?> updateInterview(@RequestBody Interview updateInterview){
        Interview currentInterview = this.interviewService.getInterviewById(updateInterview.getInterviewId());
        if(currentInterview != null){
            this.interviewService.update(updateInterview);
        }
        return new ResponseEntity("Updated Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/interviews", method = RequestMethod.POST)
    public ResponseEntity<?> addInterview(@RequestBody Interview addInterview){
        this.interviewService.add(addInterview);
        return new ResponseEntity("Added Successfully", new HttpHeaders(), HttpStatus.OK);
    }


}
