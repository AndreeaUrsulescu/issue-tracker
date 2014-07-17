package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

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

	public void create(User user){
		em.persist(user);
	}
	
	public void update(User user){
		em.merge(user);
	}

	public boolean exists(String userName){
		TypedQuery<User> query = em.createNamedQuery(User.FIND_NAME, User.class);
        query.setParameter("user_name", userName);
        if(query.getResultList().size()>0)
        	return true;
        return false;        
	}
}
