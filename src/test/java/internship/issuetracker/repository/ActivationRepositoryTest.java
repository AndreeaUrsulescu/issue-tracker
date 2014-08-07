package internship.issuetracker.repository;

import internship.issuetracker.entities.Activation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
public class ActivationRepositoryTest {

    @Autowired
    ActivationRepository activationRepository;

    Activation activation;

    private static boolean run = true;

    @Before
    public void setUp() {
        if (run) {
            activation = new Activation();
            activation.setEmail("foo@foo.foo");
            activation.setPassword("password");
            activation.setUserName("userName");
            activation.encryptPasswordAndKeyHash();
            activationRepository.create(activation);
            run = false;
        } else {
            activation = activationRepository.findActivationByKeyHash(activation.getKeyHash());
        }
    }

    @Test
    public void testCreateAndFind() {
        assert (null != activationRepository.findActivationByKeyHash(activation.getKeyHash()));
    }

    @Test
    public void testFindAndRemove() {
        activation = activationRepository.findActivationByKeyHash(activation.getKeyHash());
        activationRepository.remove(activation);
        assert (null == activationRepository.findActivationByKeyHash(activation.getKeyHash()));
    }
}
