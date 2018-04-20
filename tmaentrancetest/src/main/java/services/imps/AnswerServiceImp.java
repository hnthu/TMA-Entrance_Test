package services.imps;

import daos.AnswerDao;
import models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.AnswerService;

import java.util.List;

@Service
public class AnswerServiceImp implements AnswerService{
    private AnswerDao answerDao;

    @Autowired
    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    @Transactional
    public Answer get(String name) {
        return answerDao.get(name);
    }

    @Override
    @Transactional
    public Answer getAnswerById(int id) {
        return answerDao.getAnswerById(id);
    }

    @Override
    @Transactional
    public List<Answer> search(String searchString) {
        return answerDao.search(searchString);
    }

    @Override
    @Transactional
    public List<Answer> getAll() {
        return answerDao.getAll();
    }

    @Override
    @Transactional
    public void add(Answer newAnswer) {
        answerDao.add(newAnswer);
    }

    @Override
    @Transactional
    public void update(Answer modifiedAnswer) {
        answerDao.update(modifiedAnswer);
    }

    @Override
    @Transactional
    public void delete(int id) {
        answerDao.delete(id);
    }
}
