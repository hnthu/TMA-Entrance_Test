package daos.impls;

import daos.QuestionDao;
import models.Question;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class QuestionDaoImpl implements QuestionDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionDaoImpl.class);
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

    public List<Question> getQuestionsByProgrammingLanguage(String technical){
        Session session = this.manager.getSessionFactory().getCurrentSession();
        Criteria questionCriteria1 = session.createCriteria(Question.class);
        Criteria categoryCriteria1 = questionCriteria1.createCriteria("categoryId");
        categoryCriteria1.add(Restrictions.eq("categoryName", technical));
        questionCriteria1.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Question> questionList = questionCriteria1.list();
        return questionList;
    };

    @SuppressWarnings("unchecked")
    @Override
    public List<Question> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Question> questionList = session.createQuery("from Question").list();
        for (Question p : questionList) {
            logger.info("Question List:" + p);
        }
        return questionList;
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
