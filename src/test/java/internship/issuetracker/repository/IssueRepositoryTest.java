package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml",
	"classpath:config/application-context.xml",
	"classpath:config/Spring-Mail.xml" })
public class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    private static User user;
    private Issue issue;

    public Issue createIssue() {
	userRepository.create(user);
	
	Issue issue = new Issue();
	issue.setContent("contentx");
	issue.setTitle("titlu issue");
	issue.setOwner(user);
	issue.setUpdateDate(new Date());
	return issue;
    }

    @BeforeClass
    public static void oneTimeSetUp() {
	user = new User();
	user.setUserName("username");
	user.setEmail("email@end.com");
	user.setPassword("parola");
    }

    @Test
    public void testCreate() {
	issue = createIssue();
	issueRepository.create(issue);
	assert (issueRepository.findIssue(issue.getId()) == issue);
    }
    
    @Test
    public void testFindIssue() {
	Issue dbIssue = issueRepository.findIssue(issue.getId());
	assert (issue.equals(dbIssue));
    }

    @Test
    public void testUpdate() {
	issue = issueRepository.findIssue(issue.getId());
	issue.setTitle("boobari");
	issue.setUpdateDate(new Date());
	issueRepository.update(issue);
	assert (issueRepository.findIssue(issue.getId()).equals(issue));
    }
}
