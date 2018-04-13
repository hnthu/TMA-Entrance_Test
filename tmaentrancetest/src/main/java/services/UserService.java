package services;

import models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public User getUserById(int id);
    public User get(String name);
    public List<User> search(String searchString);
    public List<User> getAll();
    public void add(User newUser);
    public void update(User modifiedUser);
    public void delete(int id);
    public boolean isUserExist(User user);
}
