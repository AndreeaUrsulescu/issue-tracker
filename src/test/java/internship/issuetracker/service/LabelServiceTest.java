package internship.issuetracker.service;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.repository.IssueLabelRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.LabelRepository;
import internship.issuetracker.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class LabelServiceTest {

    private User user;
    private LabelPojo labelPojo;
    private Label label;
    static int count = 65;

    @Mock
    private LabelRepository labelRepository;

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private LabelService labelService = new LabelService();

    @Mock
    private IssueLabelRepository issueLabelRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        label = new Label();
        label.setId((long) 10);
        label.setLabelName("randomLabel");
        labelPojo = new LabelPojo();
        labelPojo.setLabelName(label.getLabelName());
        labelPojo.setId(label.getId());
    }

    public Issue createIssue() {
        Issue issue = new Issue();
        issue.setContent("content" + (char) count);
        issue.setTitle("title" + (char) count);
        issue.setOwner(user);
        issueRepository.create(issue);
        count++;
        return issue;
    }

    public LabelPojo createLabelPojo() {
        LabelPojo label = new LabelPojo();
        label.setLabelName("labelName" + (char) count);
        count++;
        return label;
    }

    @Test
    public void testAssignLabelToIssue() {
        user = new User();
        user.setEmail("user@user.com");
        user.setPassword("password");
        user.setUserName("User" + (char) count);
        userRepository.create(user);
        count++;

        Issue issue = createIssue();
        LabelPojo label = createLabelPojo();
        labelService.assignLabelToIssue(issue.getId(), label);
        Issue issue2 = createIssue();
        labelService.assignLabelToIssue(issue2.getId(), label);
        issue = issueRepository.findIssue(issue.getId());
        issue2 = issueRepository.findIssue(issue2.getId());
        assert (issueLabelRepository.getLabelsForIssue(issue.getId()).get(0).getId() == issueLabelRepository.getLabelsForIssue(issue2.getId()).get(0).getId());
    }

    @Test
    public void testConversions() {

        assertEquals(labelPojo.getLabelName(), labelService.convertLabelEntityToPojoLabel(label).getLabelName());
        Label labAux = new Label();
        labelService.convertPojoLabelToLabelEntity(labelPojo, labAux);
        assertEquals(labAux.getLabelName(), label.getLabelName());
    }

    @Test
    public void getAllLabelstest() {
        List<Label> labels = new ArrayList<Label>();
        labels.add(label);

        List<LabelPojo> pojoLabels = new ArrayList<LabelPojo>();
        pojoLabels.add(labelService.convertLabelEntityToPojoLabel(label));

        Mockito.when(labelRepository.findLabels()).thenReturn(labels);

        assertEquals(labelService.getAllLabels().get(0).getLabelName(), label.getLabelName());

    }
}
