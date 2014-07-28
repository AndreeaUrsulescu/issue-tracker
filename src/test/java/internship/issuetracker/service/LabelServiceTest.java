package internship.issuetracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueRepository;
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
	
	@InjectMocks
	private IssueRepository issueRepository=new IssueRepository();
	
	public Issue createIssue() {
		Issue issue = new Issue();
		issue.setContent("content"+(char)count);
		issue.setTitle("title" + (char) count);
		issue.setOwner(user);
		issueRepository.create(issue);
		issue=issueRepository.findIssuesByTitle(issue.getTitle()).get(0);
		count++;
		return issue;
	}
	
	public LabelPojo createLabelPojo()
	{
		LabelPojo label=new LabelPojo();
		label.setLabelName("labelName"+(char)count);
		count++;
		return label;
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
		Issue issue=createIssue();
		LabelPojo label=createLabelPojo();
		labelService.assignLabelToIssue(issue.getId(),label);
		Issue issue2=createIssue();
		labelService.assignLabelToIssue(issue2.getId(), label);
		issue=issueRepository.findIssuesByTitle(issue.getTitle()).get(0);
		issue2=issueRepository.findIssuesByTitle(issue2.getTitle()).get(0);
		
	}
}
