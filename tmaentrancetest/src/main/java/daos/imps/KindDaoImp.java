package daos.imps;

import daos.KindDao;
import models.Kind;
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
public class KindDaoImp implements KindDao{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(KindDaoImp.class);
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager manager;

    @Override
    public Kind get(String questionType) {
        return this.manager.getSessionFactory().getCurrentSession().get(Kind.class, questionType);
    }

    public Kind getKindById(int id) {
        return this.manager.getSessionFactory().getCurrentSession().get(Kind.class, id);
    }

    @Override
    public List<Kind> search(String searchString) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Kind> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Kind> questionTypeList = session.createQuery("from Kind").list();
        for (Kind p : questionTypeList) {
            logger.info("Kind List:" + p);
        }
        return questionTypeList;
    }

    @Override
    public void add(Kind newQuestionType) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.save(newQuestionType);
        logger.info("Kind saved successfully, Kind Details=" + newQuestionType);
    }

    @Override
    public void update(Kind modifiedQuestionType) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.update(modifiedQuestionType);
        logger.info("Kind updated successfully, Kind Details=" + modifiedQuestionType);
    }

    @Override
    public void delete(int id) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        Kind p = (Kind) session.get(Kind.class, id);
        if (null != p) {
            session.delete(p);
        }
        logger.info("Kind deleted successfully, Kind details=" + p);
    }
}
