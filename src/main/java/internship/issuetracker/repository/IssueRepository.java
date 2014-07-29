package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IssueRepository {
	private static final Logger log = Logger.getLogger( UserRepository.class.getName() );
	
	public static int itemsPerPage = 10;

	@PersistenceContext
	private EntityManager em;

	public void create(Issue issue) {
		System.out.println(issue.getId());
		em.persist(issue);
	}

	public void update(Issue issue) {
		em.merge(issue);
	}


	public int numberOfIssues() {

		return Integer.parseInt( em.createQuery("select count(a) as count from Issue a ").getSingleResult().toString());
		 
	}

	public List<Issue> findOrderedIssues(int currentPage) {

		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_ALL, Issue.class);
		query.setMaxResults(itemsPerPage);
		query.setFirstResult((currentPage - 1) * itemsPerPage);
		return query.getResultList();
	}

	public Issue findIssue(Long id) {
		TypedQuery<Issue> query = em.createNamedQuery(Issue.FIND_BY_ID, Issue.class);
		query.setParameter("id", id);
		try{
		return query.getSingleResult();
		}catch(NoResultException ex){			
			log.log( Level.FINE, "NoResultException in issueRepository.findIssue("+ id +")" );			
			return new Issue();
		}
	}


	public int nrOfPages() {
		int x = numberOfIssues();
		if (x % itemsPerPage > 0)
			return x / itemsPerPage + 1;
		else
			return x / itemsPerPage;
	}

	public int itemsPerPage() {
		return itemsPerPage;
	}
}
