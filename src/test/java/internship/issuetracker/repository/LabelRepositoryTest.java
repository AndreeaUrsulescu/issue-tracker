package internship.issuetracker.repository;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.IssueLabel;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;

import org.junit.Before;
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
public class LabelRepositoryTest {

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private IssueLabelRepository issueLabelRepository;

	private Issue issue;
	private User user;

	static int count = 65;
	static int icount = 65;
	static int ucount = 65;

	public Label createLabel() {
		Label label = new Label();
		label.setLabelName("title" + (char) count);
		labelRepository.create(label);
		IssueLabel issueLabel = new IssueLabel();
		issueLabel.setIssue(issue);
		issueLabel.setLabel(label);
		issueLabelRepository.create(issueLabel);
		count++;
		return label;
	}

	@Before
	@Transactional
	public void setUp() {
		user = new User();
		user.setUserName("user" + (char) ucount);
		user.setPassword("parola");
		user.setEmail("user@gmail.com");
		userRepository.create(user);
		ucount++;
		issue = new Issue();
		issue.setTitle("issue" + (char) icount);
		issue.setContent("content");
		issue.setOwner(user);
		issueRepository.create(issue);
		icount++;
	}

	@Test
	public void findLabelsTest() {
		createLabel();
		assert (labelRepository.findLabels().size() == 1);
	}

	@Test
	public void findLabelByNameTest() {
		Label label = createLabel();
		assertEquals(labelRepository.findLabelByName(label.getLabelName())
				.getLabelName(), label.getLabelName());
	}

	
	@Test
	public void createTest() {
		Label label = createLabel();
		assertEquals(labelRepository.findLabelByName(label.getLabelName())
				.getLabelName(), label.getLabelName());
	}

	
	@Test
	@Transactional
	public void updateTest() {
		Label label = createLabel();
		label.setLabelName("AnotherName");
		labelRepository.update(label);
		assertEquals(labelRepository.findLabelByName(label.getLabelName())
				.getLabelName(), "AnotherName");
	}
}
