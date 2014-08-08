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
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
@Transactional
public class ActivationRepositoryTest {

    @Autowired
    ActivationRepository activationRepository;

    Activation activation;
    private int testCount = 0;
    @Before
    public void setUp() {
        activation = new Activation();
        activation.setEmail("foo@foo.foo");
        activation.setPassword("password");
        activation.setUserName("userName");
        activation.encryptPasswordAndKeyHash();
        activationRepository.create(activation);
        testCount ++;
    }
    
    private String randomUsername() {
        StringBuilder sb = new StringBuilder("baseu");
        for(int i = 0; i < testCount; i++) {
            sb.append('a');
        }
        
        return sb.toString();
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
