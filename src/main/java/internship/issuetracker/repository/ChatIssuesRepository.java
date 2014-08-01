package internship.issuetracker.repository;

import internship.issuetracker.entities.ChatIssues;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ChatIssuesRepository {

	@PersistenceContext
	private EntityManager em;

	public void create(ChatIssues message) {
		em.persist(message);
	}

	public void update(ChatIssues message) {
		em.merge(message);
	}

	public List<ChatIssues> getMessages() {
		TypedQuery<ChatIssues> query = em.createNamedQuery(ChatIssues.GET_ALL_MESSAGES,	ChatIssues.class);
		return query.getResultList();
	}	
}
