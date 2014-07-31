package internship.issuetracker.repository;

import internship.issuetracker.entities.Activation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml",
	"classpath:config/application-context.xml",
	"classpath:config/Spring-Mail.xml" })
public class ActivationRepositoryTest {

    @Autowired
    ActivationRepository activationRepository;

    Activation activation;

    @Before
    public void setUp() {
	activation = new Activation();
	activation.setEmail("foo@foo.foo");
	activation.setPassword("password");
	activation.setUserName("userName");
	activation.EncryptPasswordAndKeyHash();
    }

    @Test
    public void testCreateAndFind() {
	activationRepository.create(activation);
	assert (null != activationRepository.findActivationByKeyHash(activation
		.getKeyHash()));
    }

    @Test
    public void testFindAndRemove() {
	activation = activationRepository.findActivationByKeyHash(activation.getKeyHash());
	activationRepository.remove(activation);
	assert (null == activationRepository.findActivationByKeyHash(activation
		.getKeyHash()));
    }
}
