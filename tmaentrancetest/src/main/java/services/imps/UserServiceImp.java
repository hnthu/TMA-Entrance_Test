package services.imps;

import daos.UserDao;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.UserService;

import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User get(String userName) {
        return userDao.get(userName);
    }

    @Override
    public Set<User> search(String searchString) {
        return userDao.search(searchString);
    }

    @Override
    public Set<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void add(User newUser) {
        userDao.add(newUser);
    }

    @Override
    public void update(User modifiedUser) {
        userDao.update(modifiedUser);
    }

    @Override
    public void delete(String userName) {
        userDao.delete(userName);
    }
}
