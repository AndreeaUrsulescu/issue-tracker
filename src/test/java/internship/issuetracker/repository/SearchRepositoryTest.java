package internship.issuetracker.repository;

import filters.ContentFilter;
import filters.StateFilter;
import filters.TitleFilter;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.enums.State;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml" })
public class SearchRepositoryTest {

	@Autowired
	private SearchRepository searchRepository;
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
		issue.setTitle("titluXYZ" + (char) count);
		issue.setOwner(user);
		issue.setUpdateDate(new Date());
		count++;
		return issue;
	}

	@Before
	public void setUp() {
		user = new User();
		user.setUserName("userq" + (char) ucount);
		user.setEmail("email@end.com");
		user.setPassword("parola");
		userRepository.create(user);
		ucount++;
	}
	@Test
	public void testConvert() {
		assert("updateDate".equals(searchRepository.convert("Data")));
	}

	@Test
	public void testFindOrderedIssuesForTitle() {
		for (int i = 0; i < 5; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		TitleFilter filter = new TitleFilter("titlu");
		assert(searchRepository.findOrderedIssues(filter,0,"Date","Ascendent").size()==5);
	}

	@Test
	public void testNumberOfIssuesForTitle() {
		for (int i = 0; i < 5; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		TitleFilter filter = new TitleFilter("titlu");
		assert(searchRepository.numberOfIssues(filter)==5);
	}
	
	@Test
	public void testFindOrderedIssuesForState() {
		for (int i = 0; i < 5; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		StateFilter filter = new StateFilter(State.New);
		assert(searchRepository.findOrderedIssues(filter,0,"Date","Ascendent").size()==5);
	}

	@Test
	public void testNumberOfIssuesForState() {
		for (int i = 0; i < 5; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		StateFilter filter = new StateFilter(State.New);
		assert(searchRepository.numberOfIssues(filter)==5);
	}
	
	@Test
	public void testFindOrderedIssuesForContent() {
		for (int i = 0; i < 5; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		TitleFilter filter = new TitleFilter("titlu");
		assert(searchRepository.findOrderedIssues(filter,0,"Date","Ascendent").size()==5);
	}

	@Test
	public void testNumberOfIssuesForContent() {
		for (int i = 0; i < 5; i++) {
			Issue issue = createIssue();
			issueRepository.create(issue);
		}
		ContentFilter filter = new ContentFilter("content");
		assert(searchRepository.numberOfIssues(filter)==5);
	}

}
