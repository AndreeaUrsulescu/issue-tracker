package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IssueRepository {

	@PersistenceContext
	private EntityManager em;

	public void create(Issue issue) {
		em.persist(issue);
	}

	public void update(Issue issue) {
		em.merge(issue);
	}

	public List<Issue> findIssuesByTitle(String title) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_TITLE,
				Issue.class);
		return query.setParameter("title", title.toLowerCase()).getResultList();
	}

	public List<Issue> findIssuesByDate(Date date) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_DATE,
				Issue.class);
		return query.setParameter("updateDate", date)
			.getResultList();
	}
	
	public List<Issue> findIssues() {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND,
				Issue.class);
		return query.getResultList();
	}
	
	
	public Issue findIssue(Long id){
		
		Issue issue= null;

		TypedQuery<Issue> query = em
				.createNamedQuery(Issue.FIND_ID, Issue.class);
		query.setParameter("id", id);

		try {
			issue = query.getSingleResult();
		} catch (NoResultException ex) {
		}

		return issue;
	}
}
