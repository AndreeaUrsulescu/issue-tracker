package internship.issuetracker.repository;

import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;

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
public class ResetPasswordRepository {
    private static final Logger LOG = Logger.getLogger(ResetPasswordRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public void create(ResetPassword resetPassword) {
        em.persist(resetPassword);
    }

    public void remove(ResetPassword resetPassword) {
        em.remove(this.findResetPasswordByKeyHash(resetPassword.getKeyHash()));
    }

    public ResetPassword findResetPasswordByKeyHash(String keyHash) {
        ResetPassword resetPassword;

        TypedQuery<ResetPassword> query = em.createNamedQuery(ResetPassword.FIND_KEYHASH, ResetPassword.class);
        query.setParameter("keyHash", keyHash);
        try {
            resetPassword = query.getSingleResult();
            LOG.log(Level.INFO, "An ResetPassword was found for " + resetPassword.getOwner().getUserName());
        } catch (NoResultException ex) {
            resetPassword = null;
            LOG.log(Level.INFO, "No ResetPassword was found for given key!",ex);
        }
        return resetPassword;
    }

    public boolean existsForUser(User user) {
        TypedQuery<ResetPassword> query = em.createNamedQuery(ResetPassword.FIND_USER, ResetPassword.class);
        query.setParameter("owner", user);
        return !query.getResultList().isEmpty();
    }

    public boolean existsForHash(String keyHash) {
        TypedQuery<ResetPassword> query = em.createNamedQuery(ResetPassword.FIND_KEYHASH, ResetPassword.class);
        query.setParameter("keyHash", keyHash);
        return !query.getResultList().isEmpty();
    }
}
