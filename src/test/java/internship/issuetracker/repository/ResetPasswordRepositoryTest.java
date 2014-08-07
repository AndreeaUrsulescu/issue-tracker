package internship.issuetracker.repository;

import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml",
    "classpath:config/Spring-Mail.xml" })
public class ResetPasswordRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    
    private User user2;
    
    public void userCreate() {
        if(!userRepository.exists("rrboobar")){
            user2=new User();
            user2.setUserName("rrboobar");
            user2.setEmail("notjust@mail.aa");
            user2.setPassword("parolascr");
            userRepository.create(user2);
        }
    }
    
    
    @Test
    public void testCreate() {
        userCreate();
        ResetPassword rr= new ResetPassword(user2);
        rr.setKeyHash("abcde");
        resetPasswordRepository.create(rr);
        assert(resetPasswordRepository.existsForHash("abcd") && resetPasswordRepository.existsForUser(user2));
    }


    @Test
    public void testExistsForUser() {
        assert(!resetPasswordRepository.existsForHash("inexistent"));
    }

    @Test
    public void testExistsForHash() {
        User user=new User();
        assert(!resetPasswordRepository.existsForUser(user));
    }

}
