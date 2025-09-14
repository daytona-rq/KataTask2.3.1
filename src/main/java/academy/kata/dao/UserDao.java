package academy.kata.dao;

import academy.kata.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> getUserById(int id);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(int id, User userToEdit);

    void deleteUser(int id);
}
