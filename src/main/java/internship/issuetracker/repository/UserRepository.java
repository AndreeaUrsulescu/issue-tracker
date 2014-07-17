package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRepository {
	@PersistenceContext
    private EntityManager em;
	
	public List<User> getAll(){
		TypedQuery<User> query = em.createNamedQuery(User.FIND_ALL, User.class);
        return query.getResultList();
	}
	
	public void remove(User user){
		em.remove(find(user.getId()));
	}
	
	public void create(User user){
		em.persist(user);
	}
	
	public void update(User user){
		em.merge(user);
	}
	
	public User find(Long id){
		TypedQuery<User> query = em.createNamedQuery(User.FIND, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();	
	}
}
