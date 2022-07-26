package dao.inter;

import bean.User;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll();

    public User getById(int number);

    public boolean updateUser(User u);

    public boolean addUser(User u);

    public boolean removeUser(int id);
}
