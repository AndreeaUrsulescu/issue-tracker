package internship.issuetracker.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.pojo.UserPojo;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.LabelService;
import internship.issuetracker.service.SearchService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;

@RunWith(MockitoJUnitRunner.class)
public class RestIssueControllerTest {
	@Mock
	SearchService searchService;
	@Mock
	LabelService labelService;
	@Mock
	IssueService issueService;
	@InjectMocks
	RestIssueController restIssueController=new RestIssueController();
	User user;
	UserPojo userPojo;
	Issue issue;
	IssuePojo issuePojo;
	Label label;
	LabelPojo labelPojo;
	List<LabelPojo> labelPojoList;
	HttpServletRequest request;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setEmail("random@random.fr");
		user.setPassword("randomPassword");
		user.setUserName("randomPierre");
			
		userPojo=new UserPojo();
		userPojo.setUserName(user.getUserName());
		
		issue=new Issue();
		issue.setContent("randomContent");
		issue.setOwner(user);
		issue.setTitle("randomTitle");
		issue.setUpdateDate(new Date());
		issue.setId((long)10);
		issuePojo=new IssuePojo(issue.getId(),issue.getOwner().getUserName(),issue.getTitle(),issue.getContent(),issue.getUpdateDate(),issue.getLastDate(),issue.getState()); //nice...can't obtain an IssuePojo directly from an issue
		
		label=new Label();
		label.setId((long)10);
		label.setLabelName("randomLabel");
		
		labelPojo=new LabelPojo(label.getId(),label.getLabelName());
		labelPojoList=new ArrayList<LabelPojo>();
		labelPojoList.add(labelPojo);
		labelPojoList.add(labelPojo);
		
		request=new MockHttpServletRequest();
		request.getSession().setAttribute("user", user);
	}
	@Test
	public void viewIssuesPageTest()
	{
		List<IssuePojo> issuesListPojo=new ArrayList<IssuePojo>();
		issuesListPojo.add(issuePojo);
		issuesListPojo.add(issuePojo);
		Mockito.when(issueService.getOrderedIssues(1)).thenReturn(issuesListPojo);
		List<IssuePojo> result=restIssueController.viewIssuesPage(1);
		assertEquals(result,issuesListPojo);
	}
	@Test
	public void addLabelTest()
	{
		Mockito.when(labelService.assignLabelToIssue((long)10,labelPojo)).thenReturn(false);
		assertEquals(restIssueController.addLabel((long)10, labelPojo).get("response"),"duplicate");
		Mockito.when(labelService.assignLabelToIssue((long)10,labelPojo)).thenReturn(true);
		assertEquals(restIssueController.addLabel((long)10, labelPojo).get("response"),"success");
	}
	
	@Test
	public void getLabelsTest()
	{
		Mockito.when(labelService.getAllLabels()).thenReturn(labelPojoList);
		Map<Long,String> result=restIssueController.getLabels();
		assertEquals(result.get((long)10),"randomLabel");		
	}
	@Test
	public void removeLabeltest()
	{
		Map<String,Object> result=restIssueController.removeLabel((long)10, labelPojo);
		assertEquals(result.get("response"),"success");
	}
	
	@Test
	public void assignUserTest()
	{
		Map<String,Object> response=restIssueController.assignUser((long)10, userPojo);
		assertEquals(response.get("response"),"success");
	}
	
	@Test
	public void unassignUserTest()
	{
		Mockito.when(issueService.unassignUserToIssue((long)10, user.getUserName())).thenReturn(true);
		Map<String,Object> result=restIssueController.unassignUser((long)10,request);
		assertEquals(result.get("response"),"success");
		
		Mockito.when(issueService.unassignUserToIssue((long)10, user.getUserName())).thenReturn(false);
		result=restIssueController.unassignUser((long)10,request);
		assertEquals(result.get("response"),"failure");
	}
	
	
}
