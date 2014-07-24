package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchRepository {

    public static int itemsPerPage = 10;

    @PersistenceContext
    private EntityManager em;

    public int numberOfIssues(String title) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
	Root<Issue> root = criteriaQuery.from(Issue.class);
	TitleFilter titleFilter = new TitleFilter("title");
	criteriaQuery.where(titleFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
	return query.getResultList().size();
    }

    public List<Issue> findOrderedIssues(String title, int currentPage) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
	Root<Issue> root = criteriaQuery.from(Issue.class);
	TitleFilter titleFilter = new TitleFilter("title");
	criteriaQuery.where(titleFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
	query.setMaxResults(itemsPerPage);
	query.setFirstResult((currentPage - 1) * itemsPerPage);
	return query.getResultList();
    }
    
}
