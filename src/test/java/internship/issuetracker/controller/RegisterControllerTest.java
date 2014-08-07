package internship.issuetracker.controller;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.ActivationService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.utils.MailHelper;
import internship.issuetracker.validator.ActivationValidator;
import internship.issuetracker.validator.UserValidator;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class RegisterControllerTest {

    @InjectMocks
    RegisterController registerController = new RegisterController();

    Model model;
    User user;

    HttpServletRequest request;
    @Mock
    UserValidator userValidator;
    @Mock
    ActivationValidator activationValidator;
    @Mock
    UserService userService;
    @Mock
    ActivationService activationService;
    @Mock
    BindingResult bindingResult;
    @Mock
    MailHelper mh;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new BindingAwareModelMap();
        request = new MockHttpServletRequest();
        user = new User();
        user.setEmail("random@random.fr");
        user.setPassword("randomPassword");
        user.setUserName("randomPierre");
    }

    @Test
    public void registerPageTest() {
        String response = registerController.registerPage(model);
        assertEquals(response, "register");
    }

    @Test
    public void registerUserTest() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(new ArrayList<ObjectError>());
        ModelAndView mv = registerController.registerUser(user, bindingResult, request);
        assertEquals(mv.getViewName(), "register");
        assertEquals(mv.getModel().containsKey("errors"), true);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        mv = registerController.registerUser(user, bindingResult, request);
        assertEquals(mv.getViewName(), "checkEmailPage");
        assertEquals(mv.getModel().containsKey("errors"), false);
    }

}
