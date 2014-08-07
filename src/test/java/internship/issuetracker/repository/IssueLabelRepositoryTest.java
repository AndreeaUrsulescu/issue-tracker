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
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
public class IssueLabelRepositoryTest {

    @Autowired
    private IssueLabelRepository issueLabelRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    private Label label;
    private Issue issue;
    private User user;

    static int count = 65;
    static int icount = 65;
    static int ucount = 65;
    static int lcount = 65;

    public IssueLabel createIssueLabel() {
        IssueLabel issueLabel = new IssueLabel();
        issueLabel.setIssue(issue);
        issueLabel.setLabel(label);
        issueLabelRepository.create(issueLabel);
        return issueLabel;
    }

    @Before
    @Transactional
    public void setUp() {
        user = new User();
        user.setUserName("username" + (char) ucount);
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
        label = new Label();
        label.setLabelName("label" + (char) lcount);
        labelRepository.create(label);
        lcount++;
    }

    @Test
    public void removeLabelForIssueTest() {
        createIssueLabel();
        issueLabelRepository.removeLabelFromIssue(issue.getId(), "labelA");
        assert (issueLabelRepository.findIssueLabel(issue.getId(), "labelA") == null);
    }

    @Test
    public void createTest() {
        IssueLabel issueLabel = createIssueLabel();
        assertEquals(issueLabelRepository.findIssueLabel(issue.getId(), label.getLabelName()).getId(), issueLabel.getId());
    }

    @Test
    public void deleteTest() {
        IssueLabel issueLabel = createIssueLabel();
        issueLabelRepository.delete(issueLabel.getId());
        assert (issueLabelRepository.findIssueLabel(issue.getId(), label.getLabelName()) == null);
    }

    @Test
    public void getLabelsForIssueTest() {
        for (int i = 0; i < 10; i++) {
            createIssueLabel();
        }
        assert (issueLabelRepository.getLabelsForIssue(issue.getId()).size() == 11);
    }

    @Test
    public void addLabelForIssueTest() {
        issueLabelRepository.addLabelForIssue(issue.getId(), label.getLabelName());
        assert (issueLabelRepository.getLabelsForIssue(issue.getId()).size() == 1);
    }

    @Test
    public void findIssueLabelTest() {
        IssueLabel issueLabel = createIssueLabel();
        assert (issueLabelRepository.findIssueLabel(issue.getId(), label.getLabelName()).getId() == issueLabel.getId());
    }
}
