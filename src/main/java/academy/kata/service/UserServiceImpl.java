package academy.kata.service;

import academy.kata.dao.UserDao;
import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(int id, int age, String name, boolean haveCar) {
        userDao.updateUser(id, age, name, haveCar);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }


}
