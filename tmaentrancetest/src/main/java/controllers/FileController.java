package controllers;

import models.Answer;
import models.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.AnswerService;
import services.FileService;
import services.QuestionService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {
    protected final Logger logger2 = LogManager.getLogger();
    private FileService fileService;
    private QuestionService questionService;
    private AnswerService answerService;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setQuestionervice(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) throws IOException, InvalidFormatException {

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        String [][] data = this.fileService.importData(uploadfile);
        for(int i = 1; i < data.length; i++){
            Question q = this.fileService.convertToQuestion(i , data[i][7], data[i][8], data[i][1], data[i][6]);
            this.questionService.add(q);
            Answer a = this.fileService.convertToAnswer(i , i , data[i][2], data[i][3], data[i][4], data[i][5]);
            this.answerService.add(a);
        }
        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

}
