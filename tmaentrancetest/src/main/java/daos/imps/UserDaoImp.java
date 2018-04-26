package daos.imps;

import daos.UserDao;
import models.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(UserDaoImp.class);
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager manager;

    @Override
    public User get(String userName) {
        return this.manager.getSessionFactory().getCurrentSession().get(User.class, userName);
    }
    public User getUserById(int id) {
        return this.manager.getSessionFactory().getCurrentSession().get(User.class, id);
    }
    @Override
    public List<User> search(String searchString) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        for(User p : userList){
            logger.info("User List:"+p);
        }
        return userList;
    }

    @Override
    public void add(User newUser) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.save(newUser);
        logger.info("User saved successfully, User Details="+newUser);
    }

    @Override
    public void update(User modifiedUser) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.update(modifiedUser);
        logger.info("User updated successfully, User Details="+modifiedUser);
    }

    @Override
    public void delete(int id) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        User p = (User) session.get(User.class, id);
        if(null != p){
            session.delete(p);
        }
        logger.info("User deleted successfully, User details="+p);
    }
}
