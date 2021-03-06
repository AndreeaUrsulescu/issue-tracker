package internship.issuetracker.service;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.CommentRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private static final Logger LOG = Logger.getLogger(CommentService.class.getName());

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    public void addComment(CommentPojo comment) {
        Comment com = new Comment();
        User owner = userRepository.findUserByUserName(comment.getOwner());
        Issue issue = issueRepository.findIssue(comment.getIssueId());
        com.setContent(comment.getContent());
        com.setCreationDate(comment.getCreationDate());
        com.setOwner(owner);
        com.setIssue(issue);
        this.commentRepository.create(com);
        LOG.log(Level.INFO, "An comment was created for issue " + comment.getIssueId());
    }

    public List<CommentPojo> getCommentsForIssue(IssuePojo issuePojo) {
        Issue issue = issueRepository.findIssue(issuePojo.getId());
        List<CommentPojo> pojoComments = new ArrayList<CommentPojo>();
        List<Comment> comments = commentRepository.findCommentsByIssue(issue);

        if (comments.isEmpty()) {
            LOG.log(Level.INFO, "There are no comments for issue " + issuePojo.getId());
        }

        for (Comment com : comments) {
            CommentPojo pojoComment = new CommentPojo(com.getOwner().getUserName(), com.getContent(), com.getCreationDate(), com.getIssue().getId());
            pojoComments.add(pojoComment);
        }

        return pojoComments;
    }

}
