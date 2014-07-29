package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml" })
public class IssueRepositoryTest {

	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private UserRepository userRepository;
	private User user;
	private Issue issue;


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
		user = new User();
		user.setUserName("foobari");
		user.setEmail("email@end.com");
		user.setPassword("parola");
		userRepository.create(user);
	}

	@Test
	public void testCreate() {
		issue = createIssue();
		issueRepository.create(issue);
		assert (issueRepository.findIssue(issue.getId())==issue);

	}

//	@Test
//	public void testUpdate() {		
//		issue.setTitle("boobari");
//		issue.setUpdateDate(new Date());
//		issueRepository.update(issue);
//		assert (issueRepository.findIssue(issue.getId())==issue);
//	}



	@Test
	public void testnumberOfIssues() {
		issue = createIssue();
		issueRepository.create(issue);
		assert (issueRepository.nrOfPages() == 1);
	}
	
	@Test
	public void testFindIssue() {
		issue = createIssue();
		issueRepository.create(issue);
		assert (issue.equals(issueRepository.findIssue(issue.getId())));
	}




}
