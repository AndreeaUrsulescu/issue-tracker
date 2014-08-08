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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IssueServiceTest {

    private static int testCount = 0;
    
    @Mock
    private IssueRepository issueRepository;
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IssueService issueService = new IssueService();

    private Issue issue;
    
    private User assignee;

    public Issue createIssue(User assignUser) {
        Issue issue = new Issue();
        issue.setContent("my issue");
        issue.setTitle("title");
        issue.setOwner(assignUser);
        issue.setUpdateDate(new Date());
        return issue;
    }

    public void assignIssue(User assignUser) {
        issueRepository.create(issue);
        userRepository.create(assignee);
        issue.setAssignee(assignUser);
        issueRepository.update(issue);
    }

    private String randomUsername() {
        StringBuilder sb = new StringBuilder("baseus");
        for (int i = 0; i < testCount; i++) {
            sb.append('a');
        }

        return sb.toString();
    }

    @Before
    @Transactional
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        assignee = new User();
        assignee.setUserName(randomUsername());
        assignee.setEmail("assignee@yahoo.com");
        assignee.setPassword("password");
        userRepository.create(assignee);
        testCount++;
    }

    @Test
    public void testAddIssue() {
        issue = createIssue(assignee);
        issueService.addIssue(issue);
        Mockito.verify(issueRepository).create(issue);
    }

    @Test
    public void testNumberOfIssues() {
        assert (issueService.numberOfIssues() > 0);
    }

    @Test
    public void testAssignUserToIssue() {
        issue = createIssue(assignee);
        issueRepository.create(issue);

        issue.setAssignee(assignee);
        issueRepository.update(issue);
        assert (issueRepository.findIssue(issue.getId()).getAssignee().equals(assignee));

    }

    @Test
    public void testUnassignUserToIssue1() {
        issue = createIssue(assignee);
        assignIssue(assignee);
        issue.setAssignee(null);
        issueRepository.update(issue);
        assert (issueRepository.findIssue(issue.getId()).getAssignee().equals(null));
    }

    @Test
    public void testUnassignUserToIssue2() {
        issue = createIssue(assignee);
        assignIssue(null);
        issueRepository.update(issue);
        assert (issueRepository.findIssue(issue.getId()).getAssignee().equals(null));
    }

}
