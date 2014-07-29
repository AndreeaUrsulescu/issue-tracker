package internship.issuetracker.repository;

import internship.issuetracker.entities.Activation;
import internship.issuetracker.entities.User;
import internship.issuetracker.utils.EncryptData;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ActivationRepository {
	@PersistenceContext
	private EntityManager em;

	
	public void create(Activation activation) {
		em.persist(activation);
	}
		
	public Activation findActivationByKeyHash(String keyHash)
	{
		Activation activation=new Activation();
		
		TypedQuery<Activation> query = em.createNamedQuery(Activation.FIND_KEYHASH,Activation.class);
		query.setParameter("keyHash",keyHash);
		try
		{
			activation=query.getSingleResult();
		}
		catch(NoResultException ex)
		{
			activation=null;
		}
		return activation;
	}
	
	public void remove(Activation activation)
	{
		
		em.remove(this.findActivationByKeyHash(activation.getKeyHash()));
	}
}
