package internship.issuetracker.repository;

import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;

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
public class ResetPasswordRepository {
	private static final Logger log = Logger.getLogger(ResetPasswordRepository.class.getName());

	@PersistenceContext
	private EntityManager em;

	public void create(ResetPassword resetPassword) {
		em.persist(resetPassword);
	}
	
	public void remove(ResetPassword resetPassword)
	{
		em.remove(this.findResetPasswordByKeyHash(resetPassword.getKeyHash()));
	}
	
	public ResetPassword findResetPasswordByKeyHash(String keyHash)
	{
		ResetPassword resetPassword=new ResetPassword();
		
		TypedQuery<ResetPassword> query = em.createNamedQuery(ResetPassword.FIND_KEYHASH,ResetPassword.class);
		query.setParameter("keyHash",keyHash);
		try
		{
			resetPassword=query.getSingleResult();
			log.log(Level.INFO, "An ResetPassword was found for " + resetPassword.getOwner().getUserName());
		}
		catch(NoResultException ex)
		{
			resetPassword=null;
			log.log(Level.INFO, "No ResetPassword was found for given key!");
		}
		return resetPassword;
	}
	
	public boolean exists(User user){
		TypedQuery<ResetPassword> query = em.createNamedQuery(ResetPassword.FIND_USER, ResetPassword.class);
		query.setParameter("owner", user);
		return (query.getResultList().size() > 0);
	}
}
