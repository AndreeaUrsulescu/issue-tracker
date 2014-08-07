package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private Issue issue;
    
    private Long id;

    private static boolean run = true;

    public Issue createIssue() {
        Issue issue = new Issue();
        issue.setContent("contentx");
        issue.setTitle("titlu issue");
        issue.setOwner(user);
        issue.setUpdateDate(new Date());
        return issue;
    }

    @Before
    public void setUp() {
        if (run == true) {
            user = new User();
            user.setUserName("usernam");
            user.setEmail("email@end.com");
            user.setPassword("parola");
            userRepository.create(user);
            issue = createIssue();
            issueRepository.create(issue);
            id = issue.getId();
            run = false;
        } else {
            issue = issueRepository.findIssue(id);
            id = issue.getId();
        }
    }

    @Test
    public void testCreate() {
        assert (issueRepository.findIssue(issue.getId()).equals(issue));
    }

    @Test
    public void testFindIssue() {
        Issue dbIssue = issueRepository.findIssue(issue.getId());
        assert (issue.equals(dbIssue));
    }

    @Test
    public void testUpdate() {
        Issue dbIssue = issueRepository.findIssue(issue.getId());
        dbIssue.setTitle("A randomtitle");
        issueRepository.update(dbIssue);
        assert (issueRepository.findIssue(id).equals(dbIssue));
    }
}
