package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IssueRepository {

	private static int itemsPerPage=30;
	@PersistenceContext
	private EntityManager em;

	public void create(Issue issue) {
		em.persist(issue);
	}

	public void update(Issue issue) {
		em.merge(issue);
	}

	public List<Issue> findIssuesByTitle(String title) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_BY_TITLE,
				Issue.class);
		return query.setParameter("title", title.toLowerCase()).getResultList();
	}

	public List<Issue> findIssuesByDate(Date date) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_BY_DATE,
				Issue.class);
		return query.setParameter("updateDate", date)
			.getResultList();
	}
	
	public List<Issue> findIssues() {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_ALL,
				Issue.class);
		return query.getResultList();
	}
	
	public List<Issue> findOrderedIssues() {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_ALL, Issue.class);
		return query.getResultList();
	}
	
	public Issue findIssue(Long id){
		
		Issue issue= null;

		TypedQuery<Issue> query = em
				.createNamedQuery(Issue.FIND_BY_ID, Issue.class);
		query.setParameter("id", id);

		try {
			issue = query.getSingleResult();
		} catch (NoResultException ex) {
		}

		return issue;
	}

	public List<Issue> findIssuesForPagination(int page) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_ALL, Issue.class);
        query.setMaxResults(itemsPerPage);
        query.setFirstResult(page * itemsPerPage);
        return query.getResultList();

	}
	
	public int nrOfPages(){
		int x=findIssues().size();
		if(x%itemsPerPage>0)
			return x/itemsPerPage+1;
		else
			return x/itemsPerPage;
	}
}
