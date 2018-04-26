package daos;

import models.Question;

import java.util.List;

public interface QuestionDao {
    public Question get(String questionName);
    public Question getQuestionById(int id);
    public List<Question> search(String searchString);
    public List<Question> getAll();
    public void add(Question newQuestion);
    public void update(Question modifiedQuestion);
    public void delete(int id);
}
