package internship.issuetracker.repository;

import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ResetPasswordRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;

    private User user;

    public void userCreate() {
        user = new User();
        user.setUserName("sername");
        user.setEmail("notjust@mail.aa");
        user.setPassword("parolascr");
        userRepository.create(user);
    }

    @Test
    public void testCreate() {
        userCreate();
        ResetPassword resetPassword = new ResetPassword(user);
        resetPassword.setKeyHash("abcde");
        resetPasswordRepository.create(resetPassword);
        assert (resetPasswordRepository.existsForHash("abcde") && resetPasswordRepository.existsForUser(user));
    }

    @Test
    public void testExistsForUser() {
        assert (!resetPasswordRepository.existsForHash("inexistent"));
    }

    @Test
    public void testExistsForHash() {
        assert (!resetPasswordRepository.existsForUser(user));
    }

}
