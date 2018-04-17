package daos;

import models.Answer;
import models.Question;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileDao {
    public String[][] importData(MultipartFile file) throws IOException, InvalidFormatException;
    public Question convertToQuestion(int id, String CategoryId, String QuestionTypeId, String QuestionText, String CorrectAnswer);
    public Answer convertToAnswer(int id, int QuestionId, String Answer1, String Answer2, String Answer3, String Answer4);
}
