package internship.issuetracker.controller;

import static org.junit.Assert.*;
import internship.issuetracker.entities.User;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;

public class HomeControllerTest {
    HttpServletRequest request;
    
    ModelMap model;
    User user;
    @InjectMocks
    HomeController home=new HomeController();
    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        model=new ModelMap();
        user=new User();
        request = new MockHttpServletRequest();
        
    }
    
    @Test
    public void testHome()
    {
        request.getSession().setAttribute("user", user);
        String result = home.home(request, model);
        assertEquals(result,"redirect:/issues");
        request.getSession().setAttribute("user", null);
        result = home.home(request, model);
        assertEquals(result,"home");
        assert(model.get("user")!=null);
    }
}
