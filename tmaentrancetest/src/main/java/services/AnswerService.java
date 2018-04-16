package services;

import models.Answer;
import java.util.List;

public interface AnswerService {
    public Answer get(String answerName);
    public Answer getAnswerById(int id);
    public List<Answer> search(String searchString);
    public List<Answer> getAll();
    public void add(Answer newAnswer);
    public void update(Answer modifiedAnswer);
    public void delete(int id);
}
