package services.imps;

import daos.UserDao;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.UserService;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User get(String name) {
        return userDao.get(name);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> search(String searchString) {
        return userDao.search(searchString);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void add(User newUser) {
        userDao.add(newUser);
    }

    @Override
    @Transactional
    public void update(User modifiedUser) {
        userDao.update(modifiedUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    public boolean isUserExist(User user) {
        return get(user.getName())!=null;
    }
}
