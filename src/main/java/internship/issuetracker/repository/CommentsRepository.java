package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CommentsRepository {
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
}
