package internship.issuetracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueLabelRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.LabelRepository;
import internship.issuetracker.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(MockitoJUnitRunner.class)
public class LabelServiceTest {
	User user;
	static int count=20;
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private LabelService labelService;
	
	@Autowired
	private IssueLabelRepository issueLabelRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	public Issue createIssue() {
		Issue issue = new Issue();
		issue.setContent("content"+(char)count);
		issue.setTitle("title" + (char) count);
		issue.setOwner(user);
		System.out.println("Before create");
		issueRepository.create(issue);
		System.out.println("After create");		
		System.out.println(issueRepository.numberOfIssues());
		System.out.println("After find");
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
	
	
	@Test
	public void testAssignLabelToIssue()
	{
		user=new User();
		user.setEmail("user@user.com"+(char)count);
		user.setPassword("password");
		user.setUserName("User"+(char)+count);
		userRepository.create(user);
		
		Issue issue=createIssue();
		LabelPojo label=createLabelPojo();
		labelService.assignLabelToIssue(issue.getId(),label);
		Issue issue2=createIssue();
		labelService.assignLabelToIssue(issue2.getId(), label);
		issue=issueRepository.findIssue(issue.getId());
		issue2=issueRepository.findIssue(issue.getId());
		assert(issueLabelRepository.getLabelsForIssue(issue.getId()).get(0).getId() == issueLabelRepository.getLabelsForIssue(issue2.getId()).get(0).getId());
		//assert(issue.getLabels().get(0).getId()==issue2.getLabels().get(0).getId());
		
	}
}
