package internship.issuetracker.repository;

import internship.issuetracker.entities.Activation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml" })
public class ActivationRepositoryTest {
	
	@Autowired
	ActivationRepository activationRepository;
	
	Activation activation1,activation2;
	
	@Test
	public void testCreateAndFindAndRemove()
	{
		activation1=new Activation();
		activation1.setEmail("foo@foo.foo");
		activation1.setPassword("password");
		activation1.setUserName("userName");
		activation1.EncryptPasswordAndKeyHash();
		if(null!=activationRepository.findActivationByKeyHash(activation1.getKeyHash()))
		{
			activationRepository.create(activation1);
		}
		assert(null!=activationRepository.findActivationByKeyHash(activation1.getKeyHash()));
		activationRepository.remove(activation1);
		assert(null==activationRepository.findActivationByKeyHash(activation1.getKeyHash()));
	}	
}

