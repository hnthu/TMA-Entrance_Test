package services.imps;

import daos.FileDao;
import models.Answer;
import models.Question;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import services.FileService;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImp implements FileService {
    private FileDao fileDao;

    @Autowired
    public void setQuestionDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    @Transactional
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException {
        try {
            return this.fileDao.importData(file) ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return new String[0][];
    }

    @Override
    @Transactional
    public Question convertToQuestion(int id, String CategoryId, String QuestionTypeId, String QuestionText, String CorrectAnswer, String Level){
        return this.fileDao.convertToQuestion(id, CategoryId, QuestionTypeId, QuestionText, CorrectAnswer, Level);
    }

    @Override
    @Transactional
    public Answer convertToAnswer(int id, int questionId, String answer, Question question){
        return this.fileDao.convertToAnswer(id, questionId, answer, question);
    }

    @Override
    @Transactional
    public String exportRandomExamination(String technical, String interviewName, String description){
        return this.fileDao.exportRandomExamination(technical, interviewName, description);
    }

    @Override
    @Transactional
    public String exportListAnswer(String interviewName){
        return this.fileDao.exportListAnswer(interviewName);
    }

    @Override
    @Transactional
    public String exportExamByInterviewCode(String technical, String interviewCode, String questionList){
        return this.fileDao.exportExamByInterviewCode(technical, interviewCode, questionList);
    }

}
