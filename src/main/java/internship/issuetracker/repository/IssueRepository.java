package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

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

	public Issue find(Long id) {
		TypedQuery<Issue> query = em.createQuery(
				"select issue from Issue issue where issue.id = ?1",
				Issue.class);
		Issue result = query.setParameter(1, id).getSingleResult();
		return result;

	}
}
