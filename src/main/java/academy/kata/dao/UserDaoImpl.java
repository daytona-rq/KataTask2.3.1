package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
       return entityManager
               .createQuery("SELECT u FROM User u", User.class)
               .getResultList();
    };

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, int age, String name, boolean haveCar) {
        entityManager
                .createQuery("UPDATE User u SET u.age = :age, u.name = :name, u.haveCar = :haveCar WHERE u.id = :id", User.class)
                .setParameter("age", age)
                .setParameter("name", name)
                .setParameter("haveCar", haveCar)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteUser(int id) {
        User reference = entityManager.getReference(User.class, id);
        entityManager.remove(reference);
    }
}
