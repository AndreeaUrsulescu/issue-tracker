package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.enums.State;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StateFilterTest {

    EntityManager em;
    private IssueRepository issueRepository;
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
	@SuppressWarnings("resource")
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"config/datasource/h2.xml", "config/application-context.xml");
	EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
	em = emf.createEntityManager();
	issueRepository = context.getBean(IssueRepository.class);
	userRepository = context.getBean(UserRepository.class);
	user = new User();
	user.setUserName("userx"+(char)ucount);
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
	
	assert(list.size() == 1);
	
    }

}
