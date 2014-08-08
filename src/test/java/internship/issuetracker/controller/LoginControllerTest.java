package internship.issuetracker.controller;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.User;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class LoginControllerTest {

    HttpServletRequest request;
    User user;

    @InjectMocks
    LoginController loginController = new LoginController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setEmail("random@random.fr");
        user.setPassword("randomPassword");
        user.setUserName("randomPierre");

        request = new MockHttpServletRequest();
        request.getSession().setAttribute("user", user);
    }

    @Test
    public void loginTest() {
        
        String response = loginController.login(request, null);
        assertEquals(response, "redirect:/issues");
        request.getSession().setAttribute("user", null);
        response = loginController.login(request, null);
        assertEquals(response, "login");
    
    }
}
