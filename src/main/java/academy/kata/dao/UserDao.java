package academy.kata.dao;

import academy.kata.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(int id, int age, String name, boolean haveCar);

    void deleteUser(int id);
}
