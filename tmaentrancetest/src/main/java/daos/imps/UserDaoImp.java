package daos.imps;

import daos.UserDao;
import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User get(String userName) {
        return sessionFactory.getCurrentSession().get(User.class, userName);
    }

    @Override
    public Set<User> search(String searchString) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        CriteriaBuilder builder =  sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> variableRoot = query.from(User.class);
        query.select(variableRoot);
        return new HashSet<>(sessionFactory.getCurrentSession().createQuery(query).getResultList());
    }

    @Override
    public void add(User newUser) {

    }

    @Override
    public void update(User modifiedUser) {

    }

    @Override
    public void delete(String userName) {

    }
}
