package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;

import java.util.ArrayList;
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
	TitleFilter titleFilter = new TitleFilter(title);
	criteriaQuery.where(titleFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
	return query.getResultList().size();
    }
    
    public int numberOfIssuesByContent(String content) {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
    	Root<Issue> root = criteriaQuery.from(Issue.class);
    	ContentFilter contentFilter = new ContentFilter(content);
    	criteriaQuery.where(contentFilter.buildPredicate(criteriaQuery,criteriaBuilder, root));
    	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
    	return query.getResultList().size();
    }

    public List<Issue> findOrderedIssues(String title, int currentPage) {
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
    
    public List<IssuePojo> findOrderedIssuesByContent(String content, int currentPage) {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
    	Root<Issue> root = criteriaQuery.from(Issue.class);
    	ContentFilter contentFilter = new ContentFilter(content);
    	criteriaQuery.where(contentFilter.buildPredicate(criteriaQuery, criteriaBuilder, root));
    	TypedQuery<Issue> query = em.createQuery(criteriaQuery);
    	query.setMaxResults(itemsPerPage);
    	query.setFirstResult((currentPage - 1) * itemsPerPage);
    	List<Issue> issues = query.getResultList();
    	List<IssuePojo> pojoIssues = new ArrayList<IssuePojo>();
    	
    	for (Issue issue : issues) {
    		IssuePojo pojo = new IssuePojo(issue.getId(), issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(), issue.getUpdateDate(), issue.getState());
    		pojoIssues.add(pojo);
    	}
    	
    	return pojoIssues;
    }
    
}
