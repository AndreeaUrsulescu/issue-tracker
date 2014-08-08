package internship.issuetracker.repository;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml", "classpath:config/Spring-Mail.xml" })
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    static int count = 65;
    
    User user = new User();
    Issue issue = new Issue();
    Comment comment = new Comment();

    @Before
    public void setUp() {
        
        user.setUserName("foobarxs" + (char) count);
        user.setEmail("just@mail.ro");
        user.setPassword("parola");
        userRepository.create(user);

        
        issue.setContent("content");
        issue.setTitle("title" + (char) count);
        issue.setOwner(user);
        issue.setUpdateDate(new Date());
        issueRepository.create(issue);
        count++;

        
        comment.setContent("abracadabra");
        comment.setOwner(user);
        comment.setIssue(issue);
        comment.setCreationDate(new Date());
    }

    @Test
    public void testCreate() {
        commentRepository.create(comment);
        assert (commentRepository.findCommentByOwner(comment.getOwner()).size() > 0);
    }

    @Test
    public void testFindByUser() {
        User creator = comment.getOwner();
        commentRepository.create(comment);
        assert (commentRepository.findCommentByOwner(creator).contains(comment));
    }

    @Test
    public void testFindByIssue() {
        Issue issue = comment.getIssue();
        commentRepository.create(comment);
        assert (commentRepository.findCommentsByIssue(issue).contains(comment));
    }

}
