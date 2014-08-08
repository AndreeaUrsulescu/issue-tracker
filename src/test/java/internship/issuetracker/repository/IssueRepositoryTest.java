package internship.issuetracker.repository;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class IssueRepositoryTest {
    private static int testCount = 0;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Issue issue;

    public Issue createIssue() {
        issue = new Issue();
        issue.setContent("contentx");
        issue.setTitle("titlu issue");
        issue.setOwner(user);
        issue.setUpdateDate(new Date());
        return issue;
    }

    @Before
    public synchronized void setUp() {
        user = new User();
        user.setUserName(randomUsername());
        user.setEmail("email@end.com");
        user.setPassword("parola");
        userRepository.create(user);

        issue = createIssue();
        issueRepository.create(issue);

        testCount++;
    }
    
    private String randomUsername() {
        StringBuilder sb = new StringBuilder("baseu");
        for(int i = 0; i < testCount; i++) {
            sb.append('a');
        }
        
        return sb.toString();
    }

    @Test
    public void testCreate() {
        Issue dbIssue = issueRepository.findIssue(issue.getId());
        if (issue == null || dbIssue == null) {
            Assert.fail("null values");
        }
        assertEquals(dbIssue, issue);
    }

    @Test
    public void testFindIssue() {
        Issue dbIssue = issueRepository.findIssue(issue.getId());
        if (issue == null || dbIssue == null) {
            Assert.fail("null values");
        }
        assertEquals(issue, dbIssue);
    }

    @Test
    public void testUpdate() {
        Issue dbIssue = issueRepository.findIssue(issue.getId());
        dbIssue.setLastDate(new Date());
        dbIssue.setUpdateDate(new Date());
        issueRepository.update(dbIssue);
        Issue dbFind = issueRepository.findIssue(issue.getId());
        if (issue == null || dbIssue == null) {
            Assert.fail("null values");
        }
        assertEquals(dbFind, dbIssue);
    }
}
