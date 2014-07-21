package internship.issuetracker.repository;

import java.util.Date;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommentRepositoryTest {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private IssueRepository issueRepository;

    static int count = 65;

    public Comment createComment()
    {
    	User user = new User();
    	user.setUserName("foobarxs" + (char)count);
    	user.setEmail("just@mail.ro");
    	user.setPassword("parola");
    	userRepository.create(user);

    	Issue issue = new Issue();
    	issue.setContent("content");
    	issue.setTitle("title" + (char)count);
    	issue.setOwner(user);
    	issue.setUpdateDate(new Date());
    	count++;
    	
    	Comment comment=new Comment();
    	comment.setContent("abracadabra");
    	comment.setOwner(user);
    	comment.setIssue(issue);
    	comment.setCreationDate(new Date());
    	return comment;
    }
    
	@Before
    public void setUp() {
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"config/datasource/h2.xml", "config/application-context.xml");
	CommentRepository commentRepository= context.getBean(CommentRepository.class);
	UserRepository userRepository= context.getBean(UserRepository.class);
	IssueRepository issueRepository=context.getBean(IssueRepository.class);
    }
	
	@Test
    public void testCreate() {
	Comment comment = createComment();
	commentRepository.create(comment);
	assert.findComment
    }

}
