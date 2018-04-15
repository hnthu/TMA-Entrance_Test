package daos.imps;

import daos.QuestionDao;
import models.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class QuestionDaoImp implements QuestionDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionDaoImp.class);
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager manager;

    @Override
    public Question get(String question) {
        return this.manager.getSessionFactory().getCurrentSession().get(Question.class, question);
    }

    public Question getQuestionById(int question) {
        return this.manager.getSessionFactory().getCurrentSession().get(Question.class, question);
    }

    @Override
    public List<Question> search(String searchString) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Question> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Question> questionTypeList = session.createQuery("from Question").list();
        for (Question p : questionTypeList) {
            logger.info("Question List:" + p);
        }
        return questionTypeList;
    }

    @Override
    public void add(Question newQuestionType) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.save(newQuestionType);
        logger.info("Question saved successfully, Question Details=" + newQuestionType);
    }

    @Override
    public void update(Question modifiedQuestionType) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.update(modifiedQuestionType);
        logger.info("Question updated successfully, Question Details=" + modifiedQuestionType);
    }

    @Override
    public void delete(int id) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        Question p = (Question) session.get(Question.class, id);
        if (null != p) {
            session.delete(p);
        }
        logger.info("Question deleted successfully, Question details=" + p);
    }
}
