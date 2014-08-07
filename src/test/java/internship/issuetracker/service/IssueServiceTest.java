package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.UserRepository;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IssueService issueService = new IssueService();

    private Issue issue;
    private User user;
    private User assignee;

    public Issue createIssue() {
        Issue issue = new Issue();
        issue.setContent("my issue");
        issue.setTitle("title");
        issue.setOwner(user);
        issue.setUpdateDate(new Date());
        return issue;
    }

    public void assignIssue(User assignUser) {
        issueRepository.create(issue);
        userRepository.create(assignee);
        issue.setAssignee(assignUser);
        issueRepository.update(issue);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setUserName("username");
        user.setEmail("username@yahoo.com");
        user.setPassword("password");
        userRepository.create(user);

        assignee = new User();
        assignee.setUserName("assignee");
        assignee.setEmail("assignee@yahoo.com");
        assignee.setPassword("password");

        issue = createIssue();
    }

    @Test
    public void testAddIssue() {
        issueService.addIssue(issue);
        Mockito.verify(issueRepository).create(issue);
    }

    @Test
    public void testAssignUserToIssue() {
        issueRepository.create(issue);
        userRepository.create(assignee);

        issue.setAssignee(assignee);
        issueRepository.update(issue);
        assert (issueRepository.findIssue(issue.getId()).getAssignee().equals(assignee));

    }

    @Test
    public void testUnassignUserToIssue1() {

        assignIssue(assignee);
        issue.setAssignee(null);
        issueRepository.update(issue);
        assert (issueRepository.findIssue(issue.getId()).getAssignee().equals(null));
    }

    @Test
    public void testUnassignUserToIssue2() {

        assignIssue(null);
        issueRepository.update(issue);
        assert (issueRepository.findIssue(issue.getId()).getAssignee().equals(null));
    }

}
