package services.imps;

import daos.QuestionDao;
import models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService{
    private QuestionDao questionDao;

    @Autowired
    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    @Transactional
    public Question get(String name) {
        return questionDao.get(name);
    }

    @Override
    @Transactional
    public Question getQuestionById(int id) {
        return questionDao.getQuestionById(id);
    }

    @Override
    @Transactional
    public List<Question> getQuestionsByProgrammingLanguage(String technical){
        return questionDao.getQuestionsByProgrammingLanguage(technical);
    }

    @Override
    @Transactional
    public List<Question> search(String searchString) {
        return questionDao.search(searchString);
    }

    @Override
    @Transactional
    public List<Question> getAll() {
        return questionDao.getAll();
    }

    @Override
    @Transactional
    public void add(Question newQuestion) {
        questionDao.add(newQuestion);
    }

    @Override
    @Transactional
    public void update(Question modifiedQuestion) {
        questionDao.update(modifiedQuestion);
    }

    @Override
    @Transactional
    public void delete(int id) {
        questionDao.delete(id);
    }
}
