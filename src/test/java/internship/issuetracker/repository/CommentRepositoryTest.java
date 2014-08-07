package internship.issuetracker.repository;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    static int count = 65;

    public Comment createComment() {
        User user = new User();
        user.setUserName("foobarxs" + (char) count);
        user.setEmail("just@mail.ro");
        user.setPassword("parola");
        userRepository.create(user);

        Issue issue = new Issue();
        issue.setContent("content");
        issue.setTitle("title" + (char) count);
        issue.setOwner(user);
        issue.setUpdateDate(new Date());
        issueRepository.create(issue);
        count++;

        Comment comment = new Comment();
        comment.setContent("abracadabra");
        comment.setOwner(user);
        comment.setIssue(issue);
        comment.setCreationDate(new Date());
        return comment;
    }

    @Test
    public void testCreate() {
        Comment comment = createComment();
        commentRepository.create(comment);
        assert (commentRepository.findCommentByOwner(comment.getOwner()).size() > 0);
    }

    @Test
    public void testFindByUser() {
        Comment comment = createComment();
        User creator = comment.getOwner();
        commentRepository.create(comment);
        assert (commentRepository.findCommentByOwner(creator).get(0).equals(creator));
    }

    @Test
    public void testFindByIssue() {
        Comment comment = createComment();
        Issue issue = comment.getIssue();
        commentRepository.create(comment);
        assert (commentRepository.findCommentsByIssue(issue).get(0).equals(issue));
    }

}
