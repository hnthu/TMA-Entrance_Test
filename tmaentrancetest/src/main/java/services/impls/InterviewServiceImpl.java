package services.impls;

import daos.InterViewDao;
import models.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.InterviewService;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService{
    private InterViewDao interviewDao;

    @Autowired
    public void setUserDao(InterViewDao interviewDao) {
        this.interviewDao = interviewDao;
    }

    @Override
    @Transactional
    public Interview get(String name) {
        return interviewDao.get(name);
    }

    @Override
    @Transactional
    public Interview getInterviewById(int id) {
        return interviewDao.getInterviewById(id);
    }

    @Override
    @Transactional
    public List<Interview> search(String searchString) {
        return interviewDao.search(searchString);
    }

    @Override
    @Transactional
    public List<Interview> getAll() {
        return interviewDao.getAll();
    }

    @Override
    @Transactional
    public void add(Interview newInterview) {
        interviewDao.add(newInterview);
    }

    @Override
    @Transactional
    public void update(Interview modifiedInterview) {
        interviewDao.update(modifiedInterview);
    }

    @Override
    @Transactional
    public void delete(int id) {
        interviewDao.delete(id);
    }
}
