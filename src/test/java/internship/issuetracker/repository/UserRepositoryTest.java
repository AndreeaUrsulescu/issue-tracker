package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

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
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;
    private static boolean run = true;
    
    @Before
    public void setUp(){
        if (run == true){
            user = new User();
            user.setUserName("foobar");
            user.setEmail("notjust@mail.a");
            user.setPassword("parola");
            userRepository.create(user);
            run = false;
        } else {
            user = userRepository.findUserByUserName("foobar");
            user.setUserName("foobar");
            userRepository.update(user);
        }
    }

    @Test
    public void testCreate() {
        assert (userRepository.exists("foobar") && user.equals(userRepository.findUserByUserName("foobar")));
    }

    public void testUpdate() {
        user.setUserName("notfoobar");
        userRepository.update(user);
        assert (userRepository.exists("notfoobar") && !userRepository.exists("foobar") && userRepository.findUserByUserName("notboobar").equals(user));
    }

    @Test
    public void testExists() {
        assert (userRepository.exists("foobar"));
    }

    @Test
    public void testMatchPassword() {
        assert (userRepository.matchPassword("foobar", "parola"));
    }

    @Test
    public void testFindUserByUserName() {
        assert (user.equals(userRepository.findUserByUserName("foobar")));
    }

}
