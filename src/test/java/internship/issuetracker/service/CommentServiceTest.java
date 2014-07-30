package internship.issuetracker.service;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.repository.CommentRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.UserRepository;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private CommentService commentService = new CommentService();

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
	User user = new User();
	user.setUserName("username");
	user.setEmail("email@end.com");
	user.setPassword("parola");
	userRepository.create(user);

	Issue issue = new Issue();
	issue.setTitle("Issue nr 1");
	issue.setOwner(user);
	issue.setContent("Content for issue nr 1");
	issueRepository.create(issue);

	CommentPojo commentPojo = new CommentPojo(user.getUserName(),
		"commentContent", new Date(), issue.getId());
	commentService.addComment(commentPojo);
	assert(commentRepository.findCommentByOwner(user)!=null);
    }

}
