package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TODO: load context using adnotations
public class IssueRepositoryTest {

    private IssueRepository issueRepository;
    private UserRepository userRepository;
    
    static int count =65;

    public Issue createIssue() {
	User user = new User();
	user.setUserName("userx"+ (char)count);
	user.setEmail("rp@rr.ro");
	user.setPassword("parola");
	userRepository.create(user);

	Issue issue = new Issue();
	issue.setContent("contentx");
	issue.setTitle("titlex" + (char)count);
	issue.setOwner(user);
	issue.setUpdateDate(new Date());
	count++;
	return issue;
    }

    @SuppressWarnings("resource")
	@Before
    public void setUp() {
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"config/datasource/h2.xml", "config/application-context.xml");
	issueRepository = context.getBean(IssueRepository.class);
	userRepository = context.getBean(UserRepository.class);
    }
    
    @Test
    public void testCreate() {
	Issue issue = createIssue();
	issueRepository.create(issue);
	assert (issueRepository.findIssuesByTitle(issue.getTitle()).size() > 0);

    }

    @Test
    public void testUpdate() {
	Issue issue = createIssue();
	issueRepository.create(issue);
	issue.setTitle("New dummy content");
	issueRepository.update(issue);
	List<Issue> compare = issueRepository.findIssuesByTitle(issue.getTitle());
	assert (issue.equals(compare));
    }

}
