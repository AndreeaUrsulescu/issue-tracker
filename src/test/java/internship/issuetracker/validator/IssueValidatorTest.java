package internship.issuetracker.validator;

import static org.junit.Assert.assertFalse;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.enums.State;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserService;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class IssueValidatorTest {

	@Mock
	private UserService userService;
	@Mock
	private IssueService issueService;
	private BindingResult bindingResult;

	@InjectMocks
    private IssueValidator issueValidator = new IssueValidator();
    
	private Issue issue;
	
 /*   @SuppressWarnings("deprecation")*/
	@Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	User user = new User();
	user.setUserName("Name");
	user.setEmail("email@foo.bar");
	user.setPassword("password");
	
	issue=new Issue();
	issue.setTitle("tiltu");
	issue.setContent("contentY");
	issue.setOwner(user);
	issue.setState(State.New);	
	issue.setUpdateDate(new Date());
	
	bindingResult = new BeanPropertyBindingResult(issue, "issue");
    }
    


    @Test
    public void testDateValid() {
	issueValidator.validate(issue, bindingResult);
	boolean result = bindingResult.hasErrors();
	assertFalse(result);

    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void testDateInvalid() {
    Date d=new Date();
    d.setYear(120);
    issue.setUpdateDate(d);		

	issueValidator.validate(issue, bindingResult);
	boolean result = bindingResult.hasErrors();
	assertFalse(!result);

    }
    
	@Test
    public void testDateInvalid2() {

    issue.setUpdateDate(null);		

	issueValidator.validate(issue, bindingResult);
	boolean result = bindingResult.hasErrors();
	assertFalse(!result);

    }
    
    @Test
    public void testStateInvalid() {
    	issue.setState(State.Closed);
	issueValidator.validate(issue, bindingResult);
	boolean result = bindingResult.hasErrors();
	assertFalse(!result);

    }
   

}
