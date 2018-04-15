package daos.imps;

import daos.AnswerDao;
import models.Answer;
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
public class AnswerDaoImp implements AnswerDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AnswerDaoImp.class);
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager manager;

    @Override
    public Answer get(String answer) {
        return this.manager.getSessionFactory().getCurrentSession().get(Answer.class, answer);
    }

    public Answer getAnswerById(int id) {
        return this.manager.getSessionFactory().getCurrentSession().get(Answer.class, id);
    }

    @Override
    public List<Answer> search(String searchString) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Answer> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Answer> answerList = session.createQuery("from Answer").list();
        for (Answer p : answerList) {
            logger.info("Answer List:" + p);
        }
        return answerList;
    }

    @Override
    public void add(Answer newAnswer) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.save(newAnswer);
        logger.info("Answer saved successfully, Answer Details=" + newAnswer);
    }

    @Override
    public void update(Answer modifiedAnswer) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.update(modifiedAnswer);
        logger.info("Answer updated successfully, Answer Details=" + modifiedAnswer);
    }

    @Override
    public void delete(int id) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        Answer p = (Answer) session.get(Answer.class, id);
        if (null != p) {
            session.delete(p);
        }
        logger.info("Answer deleted successfully, Answer details=" + p);
    }
}
