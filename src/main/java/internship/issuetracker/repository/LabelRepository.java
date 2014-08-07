package internship.issuetracker.repository;

import internship.issuetracker.entities.Label;

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
public class LabelRepository {
    private static final Logger LOG = Logger.getLogger(LabelRepository.class.getName());
    @PersistenceContext
    private EntityManager em;

    public List<Label> findLabels() {
        TypedQuery<Label> query = em.createNamedQuery(Label.FIND_ALL, Label.class);
        return query.getResultList();
    }

    public void create(Label label) {
        em.persist(label);
    }

    public Label findLabelByName(String labelName) {
        TypedQuery<Label> query = em.createNamedQuery(Label.FIND_BY_NAME, Label.class);
        try {
            return query.setParameter("labelName", labelName).getSingleResult();
        } catch (NoResultException ex) {
            LOG.log(Level.FINE, "NoResultException in LabelRepository.findLabelByName(" + labelName + ")", ex);
            return null;
        }
    }
}
