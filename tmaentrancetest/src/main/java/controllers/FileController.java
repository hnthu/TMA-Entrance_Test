package controllers;

import models.Answer;
import models.Question;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.AnswerService;
import services.FileService;
import services.QuestionService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {
    protected final Logger logger2 = LogManager.getLogger();
    private FileService fileService;
    private QuestionService questionService;
    private AnswerService answerService;

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
            Question q = this.fileService.convertToQuestion(i , data[i][5], data[i][6], data[i][1], data[i][3], data[i][4]);
            this.questionService.add(q);
            Answer a = this.fileService.convertToAnswer(i , q.getQuestionId(), data[i][2], q);
            this.answerService.add(a);
        }
        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/exportRandomExamination", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public  void exportRandomExamination(@RequestParam("technical") String technical, @RequestParam("interviewName") String interviewName, @RequestParam("description") String description, HttpServletResponse response) throws IOException{
        String fileName = this.fileService.exportRandomExamination(technical, interviewName, description);
        File file = new File(fileName);
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/pdf";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
        if(file.exists()){
            file.delete();
        }
    }

    @RequestMapping(value = "/exportExamByInterviewCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public  void exportExamByInterviewCode(@RequestParam("technical") String technical,@RequestParam("interviewCode") String interviewCode, @RequestParam("questionList") String questionList, HttpServletResponse response) throws IOException{
        String fileName =  this.fileService.exportExamByInterviewCode(technical, interviewCode, questionList);
        File file = new File(fileName);
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/pdf";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
        if(file.exists()){
            file.delete();
        }
    }

    @RequestMapping(value = "/exportanswerPDF/{interviewCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public  void exportAnswerPDF(HttpServletResponse response, @PathVariable("interviewCode") String interviewCode) throws IOException{
        String fileName = this.fileService.exportListAnswer(interviewCode);
        File file = new File(fileName);
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/pdf";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
        if(file.exists()){
            file.delete();
        }
    }


}
