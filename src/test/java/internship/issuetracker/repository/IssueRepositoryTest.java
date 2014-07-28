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
	static int count = 65;
	static int ucount = 65;

	public Issue createIssue() {
		Issue issue = new Issue();
		issue.setContent("contentx");
		issue.setTitle("fghgtitlux" + (char) count);
		issue.setOwner(user);
		issue.setUpdateDate(new Date());
		count++;
		return issue;
	}

	@Before
	public void setUp() {
		user = new User();
		user.setUserName("userx" + (char) ucount);
		user.setEmail("email@end.com");
		user.setPassword("parola");
		userRepository.create(user);
		ucount++;
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
		issue.setTitle("New title content");
		issueRepository.update(issue);
		List<Issue> compare = issueRepository.findIssuesByTitle(issue.getTitle());
		assert (issue.equals(compare));
	}

	@Test
	public void testFindIssuesForPagination() {
		for (int i = 0; i < 20; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		int size = issueRepository.findIssuesForPagination(0).size();
		assert (size > 0 && size < 30);
	}

	@Test
	public void testFindIssuesForPagination2() {
		for (int i = 0; i < 40; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		int size = issueRepository.findIssuesForPagination(0).size();
		assert ((size > 0 && size < 30));
	}

	@Test
	public void testNrOfPagesTest() {
		for (int i = 0; i < 40; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		assert (issueRepository.nrOfPages() == 2);
	}

	@Test
	public void testFindIssuesByTitle() {
		for (int i = 0; i < 10; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		List<Issue> list = issueRepository.findIssuesByTitle("titlu");
		for (Issue issue : list) {
			System.out.println(issue.getTitle());
		}
		assert (issueRepository.nrOfPages() > 0);
	}

}
