package internship.issuetracker.repository;

import java.util.List;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		TypedQuery<Comment> query = em.createNamedQuery(Comment.FIND_ISSUE,
				Comment.class);
		return query.setParameter("issue", issue).getResultList();
	}

	public Comment findComment(long id) {
		Comment comment = null;

		TypedQuery<Comment> query = em.createNamedQuery(Comment.FIND_ID,
				Comment.class);
		query.setParameter("id", id);

		try {
			comment = query.getSingleResult();
		} catch (NoResultException ex) {
		}
		return comment;
	}
	
	public List<Comment> findCommentByOwner(User user) {
		TypedQuery<Comment> query = em.createNamedQuery(Comment.FIND_OWNER,
				Comment.class);
		return query.setParameter("owner", user).getResultList();
	}

}
