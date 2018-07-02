package daos.impls;

import daos.InterViewDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import models.Interview;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional;

@Repository
@Transactional
public class InterviewDaoImpl implements InterViewDao{
    private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(InterviewDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager manager;

    @Override
    public Interview get(String interviewName) {
        return this.manager.getSessionFactory().getCurrentSession().get(Interview.class, interviewName);
    }
    public Interview getInterviewById(int id) {
        return this.manager.getSessionFactory().getCurrentSession().get(Interview.class, id);
    }
    @Override
    public List<Interview> search(String searchString) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Interview> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Interview> interviewList = session.createQuery("from Interview").list();
        for(Interview p : interviewList){
            logger.info("Interview List:"+p);
        }
        return interviewList;
    }

    @Override
    public void add(Interview newInterview) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.save(newInterview);
        logger.info("Interview saved successfully, Interview Details="+newInterview);
    }

    @Override
    public void update(Interview modifiedInterview) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.update(modifiedInterview);
        logger.info("Interview updated successfully, Interview Details="+modifiedInterview);
    }

    @Override
    public void delete(int id) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        Interview p = (Interview) session.get(Interview.class, id);
        if(null != p){
            session.delete(p);
        }
        logger.info("Interview deleted successfully, Interview details="+p);
    }


}
