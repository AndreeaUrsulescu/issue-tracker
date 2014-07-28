package internship.issuetracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.LabelRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LabelServiceTest {
	User user;
	static int count=20;
	@Mock
	private LabelRepository labelRepository;
	
	@InjectMocks
	private LabelService labelService=new LabelService();
	
	
	public Issue createIssue() {
		Issue issue = new Issue();
		issue.setContent("content"+(char)count);
		issue.setTitle("title" + (char) count);
		issue.setOwner(user);
		count++;
		return issue;
	}
	
	
	
	
	
	@Before	
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		user=new User();
		user.setEmail("user@user.com"+(char)count);
		user.setPassword("password");
		user.setUserName("User"+(char)+count);
		
	}
	
	@Test
	public void testAssignLabelToIssue()
	{
		
	}
}
