package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class TitleFIlterTest {

    EntityManager em;
    private IssueRepository issueRepository;
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
	TitleFilter tf = new TitleFilter("title");
	cq.where(tf.buildPredicate(cq, cb, root));
	cq.orderBy(cb.desc(root.get("updateDate")),cb.desc(root.get("id")));
	TypedQuery<Issue> tq = em.createQuery(cq);
	List<Issue> list = tq.getResultList();
	
	for (Issue issue2 : list) {
		
		System.out.println(issue2.getUpdateDate()+" "+issue2.getId());
	}
	assert(list.size() == 1);
    }

}
