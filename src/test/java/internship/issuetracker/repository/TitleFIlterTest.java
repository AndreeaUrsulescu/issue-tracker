package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.filters.TitleFilter;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
public class TitleFIlterTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;
    private User user;
    static int count = 65;
    static int ucount = 65;

    public Issue createIssue(String title) {
        Issue issue = new Issue();
        issue.setContent("contentx");
        issue.setTitle(title);
        issue.setOwner(user);
        issue.setUpdateDate(new Date());
        count++;
        return issue;
    }

    @Before
    public void setUp() {
        user = new User();
        user.setUserName("userz" + (char) ucount);
        user.setEmail("email@end.com");
        user.setPassword("parola");
        userRepository.create(user);
        ucount++;
    }

    @Test
    public void test() {
        Issue issue = createIssue("title");
        issueRepository.create(issue);
        issue = createIssue("context");
        issueRepository.create(issue);
        issue = createIssue("contact");
        issueRepository.create(issue);
        issue = createIssue("titlefsda");
        issueRepository.create(issue);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Issue> cq = cb.createQuery(Issue.class);
        Root<Issue> root = cq.from(Issue.class);
        TitleFilter tf = new TitleFilter("");
        cq.where(tf.buildPredicate(cq, cb, root));
        cq.orderBy(cb.desc(root.get("updateDate")), cb.desc(root.get("id")));
        TypedQuery<Issue> tq = em.createQuery(cq);
        List<Issue> list = tq.getResultList();
        assert (list.size() == 1);
    }

}
