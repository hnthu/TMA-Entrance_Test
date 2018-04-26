package daos.imps;

import daos.QuestionTypeDao;
import models.QuestionType;
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
public class QuestionTypeDaoImp implements QuestionTypeDao{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionTypeDaoImp.class);
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager manager;

    @Override
    public QuestionType get(String questionType) {
        return this.manager.getSessionFactory().getCurrentSession().get(QuestionType.class, questionType);
    }

    public QuestionType getQuestionTypeById(int id) {
        return this.manager.getSessionFactory().getCurrentSession().get(QuestionType.class, id);
    }

    @Override
    public List<QuestionType> search(String searchString) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionType> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<QuestionType> questionTypeList = session.createQuery("from QuestionType").list();
        for (QuestionType p : questionTypeList) {
            logger.info("QuestionType List:" + p);
        }
        return questionTypeList;
    }

    @Override
    public void add(QuestionType newQuestionType) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.save(newQuestionType);
        logger.info("QuestionType saved successfully, QuestionType Details=" + newQuestionType);
    }

    @Override
    public void update(QuestionType modifiedQuestionType) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.update(modifiedQuestionType);
        logger.info("QuestionType updated successfully, QuestionType Details=" + modifiedQuestionType);
    }

    @Override
    public void delete(int id) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        QuestionType p = (QuestionType) session.get(QuestionType.class, id);
        if (null != p) {
            session.delete(p);
        }
        logger.info("QuestionType deleted successfully, QuestionType details=" + p);
    }
}
