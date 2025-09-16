package academy.kata.service;

import academy.kata.dao.UserDao;
import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    Optional<User> hasRecordById(int id) {
        Optional<User> user = userDao.getUserById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(int id) {
        return hasRecordById(id);
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
        hasRecordById(id);
        userDao.updateUser(id, userToEdit);
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        hasRecordById(id);
        userDao.deleteUser(id);
    }

}
