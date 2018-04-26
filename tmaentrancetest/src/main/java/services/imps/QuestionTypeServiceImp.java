package services.imps;

import daos.QuestionTypeDao;
import models.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.QuestionTypeService;

import java.util.List;

@Service
public class QuestionTypeServiceImp implements QuestionTypeService {
    @Autowired
    private QuestionTypeDao questionTypeDao;

    @Autowired
    public void setQuestionTypeDao(QuestionTypeDao QuestionTypeDao) {
        this.questionTypeDao = questionTypeDao;
    }

    @Override
    @Transactional
    public QuestionType get(String name) {
        return questionTypeDao.get(name);
    }

    @Override
    @Transactional
    public QuestionType getQuestionTypeById(int id) {
        return questionTypeDao.getQuestionTypeById(id);
    }

    @Override
    @Transactional
    public List<QuestionType> search(String searchString) {
        return questionTypeDao.search(searchString);
    }

    @Override
    @Transactional
    public List<QuestionType> getAll() {
        return questionTypeDao.getAll();
    }

    @Override
    @Transactional
    public void add(QuestionType newQuestionType) {
        questionTypeDao.add(newQuestionType);
    }

    @Override
    @Transactional
    public void update(QuestionType modifiedQuestionType) {
        questionTypeDao.update(modifiedQuestionType);
    }

    @Override
    @Transactional
    public void delete(int id) {
        questionTypeDao.delete(id);
    }
}
