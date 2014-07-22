package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TODO: load context using adnotations
public class IssueRepositoryTest {

    private IssueRepository issueRepository;
    private UserRepository userRepository;
    private User user;
    static int count = 65;
    static int ucount = 65;

    public Issue createIssue() {
	Issue issue = new Issue();
	issue.setContent("contentx");
	issue.setTitle("titlex" + (char)count);
	issue.setOwner(user);
	issue.setUpdateDate(new Date());
	count++;
	return issue;
    }

    @SuppressWarnings("resource")
	@Before
    public void setUp() {
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"config/datasource/h2.xml", "config/application-context.xml");
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
    public void testCreate() {
	Issue issue = createIssue();
	issueRepository.create(issue);
	assert (issueRepository.findIssuesByTitle(issue.getTitle()).size() > 0);

    }

    @Test
    public void testUpdate() {
	Issue issue = createIssue();
	issueRepository.create(issue);
	issue.setTitle("New dummy content");
	issueRepository.update(issue);
	List<Issue> compare = issueRepository.findIssuesByTitle(issue.getTitle());
	assert (issue.equals(compare));
    }
    
    @Test
    public void findIssuesForPagination() {
    	for (int i = 0; i < 20; i++) {
    		Issue issue = createIssue();
    		issueRepository.create(issue);
		}
    	int size=issueRepository.findIssuesForPagination(0).size();
    	assert(size>0 && size<30);
    }
    
    @Test
    public void findIssuesForPagination2() {
    	for (int i = 0; i < 40; i++) {
    		Issue issue = createIssue();
    		issueRepository.create(issue);
		}
    	int size=issueRepository.findIssuesForPagination(0).size();   	
    	assert((size>0 && size<30));
    }
    
    @Test
    public void nrOfPagesTest(){
    	for (int i = 0; i < 40; i++) {
    		Issue issue = createIssue();
    		issueRepository.create(issue);
		}
    	assert(issueRepository.nrOfPages()==2);
    }
}
