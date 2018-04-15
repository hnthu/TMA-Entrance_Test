package daos;

import models.QuestionType;

import java.util.List;

public interface QuestionTypeDao {
    public QuestionType get(String questionType);
    public QuestionType getQuestionTypeById(int id);
    public List<QuestionType> search(String searchString);
    public List<QuestionType> getAll();
    public void add(QuestionType newQuestionType);
    public void update(QuestionType modifiedQuestionType);
    public void delete(int id);
}
