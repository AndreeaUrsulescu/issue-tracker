package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.utils.ApplicationParameters;

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
public class IssueRepository {
    private static final Logger LOG = Logger.getLogger(UserRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    private static final int ITEMS_PER_PAGE = ApplicationParameters.ITEMS_PER_PAGE;

    public void create(Issue issue) {
        em.persist(issue);
    }

    public void update(Issue issue) {
        em.merge(issue);
    }

    public int numberOfIssues() {

        return Integer.parseInt(em.createQuery("select count(a) as count from Issue a ").getSingleResult().toString());

    }

    public List<Issue> findOrderedIssues(int currentPage) {

        TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_ALL, Issue.class);
        query.setMaxResults(ITEMS_PER_PAGE);
        query.setFirstResult((currentPage - 1) * ITEMS_PER_PAGE);
        return query.getResultList();
    }

    public Issue findIssue(Long id) {
        TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_BY_ID, Issue.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            LOG.log(Level.FINE, "NoResultException in issueRepository.findIssue(" + id + ")", ex);
            return new Issue();
        }
    }

}
