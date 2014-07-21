package internship.issuetracker.repository;

import java.util.Date;
import java.util.List;

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
    	issueRepository.create(issue);
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
	@SuppressWarnings("resource")
	ApplicationContext context = new ClassPathXmlApplicationContext(
		"config/datasource/h2.xml", "config/application-context.xml");
	 commentRepository= context.getBean(CommentRepository.class);
	 userRepository= context.getBean(UserRepository.class);
	 issueRepository=context.getBean(IssueRepository.class);
    }
	
	@Test
    public void testCreate() {
	Comment comment = createComment();
	commentRepository.create(comment);
	assert(commentRepository.findCommentByOwner(comment.getOwner()).size()>0);
    }
	
	@Test
    public void testUpdate() {
	Comment comment = createComment();
	commentRepository.create(comment);
	comment.setContent("New content");
	commentRepository.update(comment);
	List<Comment> compare=commentRepository.findCommentByOwner(comment.getOwner());
	assert(comment.equals(compare));
    }

}
