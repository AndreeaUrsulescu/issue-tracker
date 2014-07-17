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
		TypedQuery<User> query = em.createNamedQuery("Select * from users;", User.class);
        return query.getResultList();
	}
	
	public void remove(User user){
		em.remove(user);
	}
	
	public void create(User user){
		em.persist(user);
	}
	
	public void update(User user){
		em.merge(user);
	}
	
	public User find(long id){
		
		return null;		
	}
}
