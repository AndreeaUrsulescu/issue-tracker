package internship.issuetracker.controller;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.UserPojo;
import internship.issuetracker.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController = new UserController();
    User user;
    UserPojo userPojo;
    List<UserPojo> userPojoList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setEmail("random@random.fr");
        user.setPassword("randomPassword");
        user.setUserName("randomPierre");
        userPojo = new UserPojo();
        userPojo.setUserName(user.getUserName());
        userPojoList = new ArrayList<UserPojo>();
        userPojoList.add(userPojo);
    }

    @Test
    public void viewAllUsersTest() {
        Mockito.when(userService.findAllUsers()).thenReturn(userPojoList);
        Map<String, Object> result = userController.viewAllUsers();
        assertEquals(result.get("usersList"), userPojoList);
    }
}
