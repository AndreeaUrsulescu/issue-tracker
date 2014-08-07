package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRepository {
    private static final Logger LOG = Logger.getLogger(UserRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_ALL, User.class);
        return query.getResultList();
    }

    public void create(User user) {
        em.persist(user);
    }

    public void update(User user) {
        em.merge(user);
    }

    public boolean exists(String userName) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_NAME, User.class);
        query.setParameter("user_name", userName.toCharArray());
        return !query.getResultList().isEmpty();
    }

    public boolean matchPassword(String userName, String password) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_PASS, User.class);
        query.setParameter("user_name", userName);
        query.setParameter("user_password", password);
        return !query.getResultList().isEmpty();
    }

    public User findUserByUserName(String userName) {

        User user = new User();

        TypedQuery<User> query = em.createNamedQuery(User.FIND_NAME, User.class);
        query.setParameter("user_name", userName);

        try {
            user = query.getSingleResult();
        } catch (NoResultException ex) {
            LOG.log(Level.FINE, "NoResultException in userRepository.findUserByUserName("+ userName+")",ex);
        }

        return user;
    }
}
