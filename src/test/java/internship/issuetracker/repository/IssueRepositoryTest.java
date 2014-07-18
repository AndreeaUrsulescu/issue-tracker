package internship.issuetracker.repository;

import internship.issuetracker.entities.Issue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IssueRepositoryTest {

    private IssueRepository issueRepository;
    private Issue issue;

    @Before
    public void setUp() {
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"config/datasource/h2.xml", "config/application-context.xml");
	issueRepository = context.getBean(IssueRepository.class);
	issue = new Issue();
	// TODO: add getters and setters
    }
    
    @Test
    public void testCreate(){
	
    }
    
    @Test
    public void testUpdate(){
	issueRepository.create(issue);
	//TODO: change a field adn update
    }

}
