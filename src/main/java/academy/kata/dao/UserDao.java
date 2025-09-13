package academy.kata.dao;

import academy.kata.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(int id, String name, int age, boolean haveCar);

    void deleteUser(int id);
}
