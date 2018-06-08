package services;

import models.Answer;
import models.Question;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileService {
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException;
    public Question convertToQuestion(int id, String CategoryId, String QuestionTypeId, String QuestionText, String CorrectAnswer, String Level);
    public Answer convertToAnswer(int id, int  QuestionId, String Answer, Question question);
    public String exportRandomExamination(String technical, String interviewName, String description);
    public String exportListAnswer(String interviewName);
    public String exportExamByInterviewCode(String technical, String interviewCode, String questionList);
}
