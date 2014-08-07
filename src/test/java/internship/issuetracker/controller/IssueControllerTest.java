package internship.issuetracker.controller;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.service.CommentService;
import internship.issuetracker.service.IssueService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;

@RunWith(MockitoJUnitRunner.class)
public class IssueControllerTest {
    @Mock
    CommentService commentService;

    @Mock
    IssueService issueService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    IssueController issueController = new IssueController();

    HttpServletRequest request;

    User user;
    Issue issue;
    IssuePojo issuePojo;
    Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setEmail("random@random.fr");
        user.setPassword("randomPassword");
        user.setUserName("randomPierre");

        issue = new Issue();
        issue.setContent("randomContent");
        issue.setOwner(user);
        issue.setTitle("randomTitle");
        issue.setUpdateDate(new Date());
        issue.setId((long) 10);

        issuePojo = new IssuePojo(issue.getId(), issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(), issue.getUpdateDate(), issue.getLastDate(), issue.getState()); // nice...can't
                                                                                                                                                                                      // obtain
                                                                                                                                                                                      // an
                                                                                                                                                                                      // IssuePojo
                                                                                                                                                                                      // directly
                                                                                                                                                                                      // from
                                                                                                                                                                                      // an
                                                                                                                                                                                      // issue

        model = new BindingAwareModelMap();
        request = new MockHttpServletRequest();
        request.getSession().setAttribute("user", user);
    }

    @Test
    public void createIssuePageTest1() {
        String view = issueController.createIssuePage(model, request);
        assertEquals(view, "createIssue");
        Map<String, Object> attributesOfTheModel = model.asMap();
        assertEquals(attributesOfTheModel.get("user"), user.getUserName());
        assert (attributesOfTheModel.containsValue("issue"));
        assert (attributesOfTheModel.containsValue("date"));
    }

    @Test
    public void createIssuePageTest2() {
        issue.setOwner(null);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String view = issueController.createIssuePage(issue, request, bindingResult);
        assertEquals(view, "createIssue");

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        view = issueController.createIssuePage(issue, request, bindingResult);

        assertEquals(view, "redirect:/issues");
        assertEquals(issue.getOwner(), user);
    }

    @Test
    public void viewIssuePageTest() {
        Mockito.when(issueService.getIssue((long) 10)).thenReturn(issuePojo);
        String view = issueController.viewIssuePage((long) 10, model);
        assertEquals(issuePojo, model.asMap().get("viewIssue"));
        assertEquals(view, "viewIssue");
    }

    @Test
    public void editIssueTest() {
        Mockito.when(issueService.getIssue((long) 10)).thenReturn(issuePojo);
        assert (((IssuePojo) issueController.editIssue((long) 10).get("issue")).equals(issuePojo));
    }

    @Test
    public void updateIssueTest() {
        Mockito.when(issueService.getIssue((long) 10)).thenReturn(issuePojo);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        assertEquals((IssuePojo) issueController.updateIssue((long) 10, issue, bindingResult).get("issue"), issuePojo);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        assertEquals((String) issueController.updateIssue((long) 10, issue, bindingResult).get("issue"), "success");
    }

    @Test
    public void AddCommentTest() {
        Comment comment = new Comment();
        comment.setContent("randomCommentContent");
        comment.setCreationDate(new Date());
        comment.setId(issue.getId());
        comment.setIssue(issue);
        comment.setOwner(user);

        CommentPojo commentPojo = new CommentPojo(comment.getOwner().getUserName(), comment.getContent(), comment.getCreationDate(), issue.getId());
        List<CommentPojo> commentPojos = new ArrayList<CommentPojo>();
        commentPojos.add(commentPojo);
        Mockito.when(commentService.getCommentsForIssue(issuePojo)).thenReturn(commentPojos);
        Mockito.when(issueService.getIssue((long) 10)).thenReturn(issuePojo);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Map<String, Object> result = issueController.addComment(comment, (long) 10, bindingResult, request);
        assertEquals(result.get("code"), "error");
        assertEquals(result.get("comments"), commentPojos);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        result = issueController.addComment(comment, (long) 10, bindingResult, request);
        assertEquals(result.get("code"), "success");
        assertEquals(result.get("comments"), commentPojos);
    }

}
