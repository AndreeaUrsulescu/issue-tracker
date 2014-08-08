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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
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
    private IssueLabel issueLabel;

    static int count = 65;

    Long id;
    private static boolean run = true;
    private static boolean removed = false;

    public void createIssueLabel() {
        issueLabel = new IssueLabel();
        issueLabel.setIssue(issue);
        issueLabel.setLabel(label);
        issueLabelRepository.create(issueLabel);
    }

    @Before
    @Transactional
    public void setUp() {
        if (run == true) {
            user = new User();
            user.setUserName("username");
            user.setPassword("parola");
            user.setEmail("user@gmail.com");
            userRepository.create(user);

            issue = new Issue();
            issue.setTitle("issue");
            issue.setContent("content");
            issue.setOwner(user);
            issueRepository.create(issue);
            id= issue.getId();

            label = new Label();
            label.setLabelName("label");
            labelRepository.create(label);
            
            createIssueLabel();
            
            run = false;
        } else {
            if (removed){
                createIssueLabel();
            }
            label = labelRepository.findLabelByName("label");
            issue = issueRepository.findIssue(id);
        }
    }

    @Test
    public void createTest() {
        assertEquals(issueLabelRepository.findIssueLabel(issue.getId(), label.getLabelName()).getId(), issueLabel.getId());
    }

    @Test
    public void removeLabelForIssueTest() {
        issueLabelRepository.removeLabelFromIssue(issue.getId(), label.getLabelName());
        removed = true;
        assert (issueLabelRepository.findIssueLabel(issue.getId(), label.getLabelName()) == null);
    }

    @Test
    public void deleteTest() {
        issueLabelRepository.delete(issueLabel.getId());
        assert (issueLabelRepository.findIssueLabel(issue.getId(), label.getLabelName()) == null);
    }

    @Test
    public void getLabelsForIssueTest() {
        assert (issueLabelRepository.getLabelsForIssue(issue.getId()).size() > 0);
    }

    @Test
    public void addLabelForIssueTest() {
        assert (issueLabelRepository.getLabelsForIssue(issue.getId()).size() == 1);
    }

    @Test
    public void findIssueLabelTest() {
        assert (issueLabelRepository.findIssueLabel(issue.getId(), label.getLabelName()).getId() == issueLabel.getId());
    }
}
