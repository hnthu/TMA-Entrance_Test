package services;

import models.User;

import java.util.Set;

public interface UserService {
    public User get(String userName);
    public Set<User> search(String searchString);
    public Set<User> getAll();
    public void add(User newUser);
    public void update(User modifiedUser);
    public void delete(String userName);
}
