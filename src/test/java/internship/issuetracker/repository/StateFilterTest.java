package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.enums.State;
import internship.issuetracker.filters.StateFilter;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class StateFilterTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    static int count = 65;
    static int ucount = 65;

    public Issue createIssue(State state) {
        Issue issue = new Issue();
        issue.setContent("contentx");
        issue.setTitle("title");
        issue.setState(state);
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
    public void test() {
        Issue issue = createIssue(State.New);
        issueRepository.create(issue);
        issue = createIssue(State.Opened);
        issueRepository.create(issue);
        issue = createIssue(State.Opened);
        issueRepository.create(issue);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Issue> cq = cb.createQuery(Issue.class);
        Root<Issue> root = cq.from(Issue.class);
        StateFilter tf = new StateFilter(State.New);
        cq.where(tf.buildPredicate(cq, cb, root));
        TypedQuery<Issue> tq = em.createQuery(cq);
        List<Issue> list = tq.getResultList();

        assert (list.size() == 1);

    }

}
