package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, User userToEdit) {
        String hql = "UPDATE User u SET u.name = :name, u.age = :age, u.hasCar = :hasCar WHERE u.id = :id";
        entityManager.createQuery(hql)
                .setParameter("name", userToEdit.getName())
                .setParameter("age", userToEdit.getAge())
                .setParameter("hasCar", userToEdit.isHasCar())
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteUser(int id) {
        User reference = entityManager.getReference(User.class, id);
        entityManager.remove(reference);
    }
}
