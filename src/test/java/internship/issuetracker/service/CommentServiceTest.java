package internship.issuetracker.service;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.CommentRepository;

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

	@InjectMocks
	private CommentService commentService = new CommentService();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		User user = new User();
		user.setUserName("testxyz");
		user.setPassword("testpass");	
		
		Issue issue = new Issue();
		issue.setTitle("Issue nr 1");
		issue.setContent("Content for issue nr 1");

		Comment comment = new Comment();
		comment.setContent("comementezi");
		comment.setIssue(issue);
		comment.setOwner(user);
		comment.setCreationDate(new Date());
				
		//commentService.addComment(comment);
		Mockito.verify(commentRepository).create(comment);
	}

}
