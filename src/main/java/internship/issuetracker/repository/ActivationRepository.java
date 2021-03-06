package internship.issuetracker.repository;

import internship.issuetracker.entities.Activation;

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
public class ActivationRepository {

    private static final Logger LOG = Logger.getLogger(ActivationRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public void create(Activation activation) {
        em.persist(activation);
    }

    public Activation findActivationByKeyHash(String keyHash) {
        Activation activation;

        TypedQuery<Activation> query = em.createNamedQuery(Activation.FIND_KEYHASH, Activation.class);
        query.setParameter("keyHash", keyHash);
        try {
            activation = query.getSingleResult();
            LOG.log(Level.INFO, "An activation was found for " + activation.getUserName());
            return activation;
        } catch (NoResultException ex) {
            LOG.log(Level.INFO, "No activation was found for given key!", ex);
            return null;
        }

    }

    public void remove(Activation activation) {
        em.remove(this.findActivationByKeyHash(activation.getKeyHash()));
    }
}
