package daos;

import models.Answer;
import models.Question;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileDao {
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException;
    public Question convertToQuestion(int id, String CategoryId, String QuestionTypeId, String QuestionText, String CorrectAnswer, String Level);
    public Answer convertToAnswer(int id, int questionId, String answer, Question question);
    public void exportPDF(String technical, String interviewName);
}
