package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }
    @Transactional
    public void deleteUserById(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
    @Transactional
    public void updateUser(int id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setAge(user.getAge());
    }
    @Transactional
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT users FROM User users", User.class).getResultList();
    }



}
