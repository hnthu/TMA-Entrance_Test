package controllers;

import models.Answer;
import models.Category;
import models.Kind;
import models.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.AnswerService;
import services.CategoryService;
import services.KindService;
import services.QuestionService;

@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class QuestionController {
    protected final Logger logger2 = LogManager.getLogger();
    private QuestionService questionService;
    private CategoryService categoryService;
    private KindService kindService;
    private AnswerService answerService;


    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public Object getAllQuestions() {
        return questionService.getAll();
    }

    @RequestMapping(value = "/questions/categories/{technical}", method = RequestMethod.GET)
    public Object getQuestionsByProgrammingLanguage(@PathVariable("technical") String technical) {
        return questionService.getQuestionsByProgrammingLanguage(technical);
    }

    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    public Object getQuestionById(@PathVariable("id") int id) {
        return questionService.getQuestionById(id);
    }

    @RequestMapping(value ="/questions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") int id){
        Question existingQuestion = this.questionService.getQuestionById(id);
        if(existingQuestion != null){
            this.questionService.delete(id);
        }
        return new ResponseEntity("Deleted Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/questions", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuestion(@RequestBody Question updateQuestion){
        Question currentQuestion = this.questionService.getQuestionById(updateQuestion.getQuestionId());
        if(currentQuestion != null){
            this.questionService.update(updateQuestion);
        }
        return new ResponseEntity("Updated Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public ResponseEntity<?> addQuestion(@RequestParam("categoryId") int categoryId, @RequestParam("kindId") int kindId,
                                         @RequestParam("answerId") int answerId, @RequestBody Question ques){
        Category cat = categoryService.getCategoryById(categoryId);
        Kind kind = kindService.getKindById(kindId);
        Answer ans = answerService.getAnswerById(answerId);
        ques.setCategory(cat);
        ques.setKind(kind);
        ques.setAnswer(ans);
        this.questionService.add(ques);
        return new ResponseEntity("Added Successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
