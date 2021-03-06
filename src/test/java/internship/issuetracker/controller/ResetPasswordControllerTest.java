package internship.issuetracker.controller;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.ResetPasswordService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.utils.MailHelper;
import internship.issuetracker.utils.UserName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ResetPasswordControllerTest {

    @Mock
    MailHelper mh;
    @Mock
    UserService userService;
    @Mock
    ResetPasswordService resetPasswordService;
    @InjectMocks
    ResetPasswordController resetPasswordController = new ResetPasswordController();
    User user = new User();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userService.findUserByUserName("randomPierre")).thenReturn(user);
        Mockito.when(resetPasswordService.getResetPassword("randomPassword")).thenReturn(new ResetPassword(user));
        user.setEmail("random@random.fr");
        user.setPassword("randomPassword");
        user.setUserName("randomPierre");
    }

    @Test
    public void resetPasswordTest() {
        Mockito.when(resetPasswordService.existsResetPasswordForHash(user.getPassword())).thenReturn(false);
        String result = resetPasswordController.resetPassword(new UserName(user), user.getPassword());
        assertEquals(result, "resetPasswordFailure");

        Mockito.when(resetPasswordService.existsResetPasswordForHash(user.getPassword())).thenReturn(true);
        result = resetPasswordController.resetPassword(new UserName(user), user.getPassword());
        assertEquals(result, "resetPasswordSuccess");
    }

}
