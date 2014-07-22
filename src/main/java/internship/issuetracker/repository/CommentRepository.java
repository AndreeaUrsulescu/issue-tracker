package internship.issuetracker.repository;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentRepository {

	@PersistenceContext
	private EntityManager em;

	public void create(Comment comment) {
		em.persist(comment);
	}

	public void update(Comment comment) {
		em.merge(comment);
	}

	public List<Comment> findCommentsByIssue(Issue issue) {
		TypedQuery<Comment> query = em.createNamedQuery(Comment.FIND_COMMENTS_BY_ISSUE,
				Comment.class);
		
		return query.setParameter("issue", issue).getResultList();
	}

	
	public List<Comment> findCommentByOwner(User user) {
		TypedQuery<Comment> query = em.createNamedQuery(Comment.FIND_COMMENTS_BY_OWNER,
				Comment.class);
		return query.setParameter("owner", user).getResultList();
	}

}
