package academy.kata.service;

import academy.kata.dao.UserDao;
import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(int id, User userToEdit) {
        getUserById(id);
        userDao.updateUser(id, userToEdit);
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        getUserById(id);
        userDao.deleteUser(id);
    }

}
