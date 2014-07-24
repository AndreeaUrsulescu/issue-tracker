package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.enums.State;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SearchRepository {

    public static int itemsPerPage = 10;

    @PersistenceContext
    private EntityManager em;

    public int numberOfIssuesByTitle(String title) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
	Root<Issue> root = criteriaQuery.from(Issue.class);
	TitleFilter titleFilter = new TitleFilter(title);
	criteriaQuery.where(titleFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
	return query.getResultList().size();
    }

    public List<Issue> findOrderedIssuesByTitle(String title, int currentPage) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
	Root<Issue> root = criteriaQuery.from(Issue.class);
	TitleFilter titleFilter = new TitleFilter(title);
	criteriaQuery.where(titleFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
	query.setMaxResults(itemsPerPage);
	query.setFirstResult((currentPage - 1) * itemsPerPage);
	return query.getResultList();
    }
    
    public int numberOfIssuesByState(State state) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
	Root<Issue> root = criteriaQuery.from(Issue.class);
	StateFilter stateFilter = new StateFilter(state);
	criteriaQuery.where(stateFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
	return query.getResultList().size();
    }

    public List<Issue> findOrderedIssuesByState(State state, int currentPage) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
	Root<Issue> root = criteriaQuery.from(Issue.class);
	StateFilter stateFilter = new StateFilter(state);
	criteriaQuery.where(stateFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
	query.setMaxResults(itemsPerPage);
	query.setFirstResult((currentPage - 1) * itemsPerPage);
	return query.getResultList();
    }
    
    
}
