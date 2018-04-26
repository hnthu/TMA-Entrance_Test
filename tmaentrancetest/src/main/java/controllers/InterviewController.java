package controllers;

import models.Interview;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.InterviewService;

@RestController
public class InterviewController {
    protected final Logger logger2 = LogManager.getLogger();
    private InterviewService interviewService;

    @Autowired
    public void setInterviewService(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @RequestMapping(value = "/getallinterviews", method = RequestMethod.GET)
    public Object getAllInterviews() {
        logger2.info("is running get all Interview in the database.");
        logger2.debug("is running get all Interview in the database.");
        logger2.error("is running get all Interview in the database.");
        return interviewService.getAll();
    }

    @RequestMapping(value = "/getinterviewbyid/{id}", method = RequestMethod.GET)
    public Object getInterviewById(@PathVariable("id") int id) {
        return interviewService.getInterviewById(id);
    }

    @RequestMapping(value ="/deleteinterview/{id}", method = RequestMethod.DELETE)
    public String deleteInterview(@PathVariable("id") int id){
        Interview existingInterview = this.interviewService.getInterviewById(id);
        if(existingInterview != null){
            this.interviewService.delete(id);
        }
        return "Deleted Successfully";
    }

    @RequestMapping(value = "/updateinterview/{id}", method = RequestMethod.PUT)
    public Object updateInterview(@PathVariable("id") int id,  @RequestBody Interview updateInterview){
        Interview currentInterview = this.interviewService.getInterviewById(id);
        if(currentInterview != null){
            this.interviewService.update(updateInterview);
        }
        return "Updated successfully";
    }

    @RequestMapping(value = "/addinterview", method = RequestMethod.POST)
    public String addInterview(@RequestBody Interview addInterview){
        this.interviewService.add(addInterview);
        return "Added successfully";
    }
}
