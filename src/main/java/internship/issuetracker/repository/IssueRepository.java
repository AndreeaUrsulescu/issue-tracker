package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
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

	public List<Issue> findTitle(String title) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_TITLE,
				Issue.class);
		List<Issue> result = query.setParameter("title", title.toLowerCase()).getResultList();
		return result;
	}

	public List<Issue> findTitle(Date date) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_DATE,
				Issue.class);
		List<Issue> result = query.setParameter("updateDate", date)
				.getResultList();
		return result;
	}
}
