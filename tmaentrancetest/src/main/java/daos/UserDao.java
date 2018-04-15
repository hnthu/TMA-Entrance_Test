package daos;

import models.User;

import java.util.List;

public interface UserDao {
    public User get(String userName);
    public User getUserById(int id);
    public List<User> search(String searchString);
    public List<User> getAll();
    public void add(User newUser);
    public void update(User modifiedUser);
    public void delete(int id);
}
