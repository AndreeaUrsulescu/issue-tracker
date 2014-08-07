package internship.issuetracker.service;

import internship.issuetracker.entities.Attachment;
import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Email;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.AttachmentPojo;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.pojo.UserPojo;
import internship.issuetracker.repository.IssueLabelRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.UserRepository;
import internship.issuetracker.utils.ApplicationParameters;
import internship.issuetracker.utils.IssueDifference;
import internship.issuetracker.utils.MailHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    private static final Logger LOG = Logger.getLogger(IssueService.class.getName());

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueLabelRepository issueLabelRepository;

    @Autowired
    private LabelService labelService;

    @Autowired
    private MailHelper mh;

    public void addIssue(Issue issue) {
        this.issueRepository.create(issue);
        LOG.log(Level.INFO, "Issue " + issue.getId() + " was created");
    }

    public void updateIssue(Issue issue) {
        Issue newIssue = issueRepository.findIssue(issue.getId());
        newIssue.setContent(issue.getContent());
        newIssue.setTitle(issue.getTitle());
        newIssue.setLastDate(new Date());
        newIssue.setState(issue.getState());

        if (null == newIssue.getAssignee()) {
            LOG.log(Level.INFO, "Issue " + newIssue.getId() + " doesn't have an assignee , no email send.");

        } else {

            String x = IssueDifference.generateDifference(newIssue, issueRepository.findIssue(issue.getId()));
            Email email = new Email();
            email.setTo(newIssue.getAssignee().getEmail());
            email.setSubject("IssueTracker - UpdateIssue");
            email.setContent(x);

            mh.setUp(email);
            new Thread(mh).start();
            LOG.log(Level.INFO, "Email send to assignee of issue " + newIssue.getId());
        }

        this.issueRepository.update(newIssue);
        LOG.log(Level.INFO, "Issue " + newIssue.getId() + " was updated");
    }

    public IssuePojo getIssue(Long id) {
        List<CommentPojo> pojoComments = new ArrayList<CommentPojo>();
        List<LabelPojo> labelPojoList = new ArrayList<LabelPojo>();
        Issue issue = this.issueRepository.findIssue(id);

        for (Comment com : issue.getComments()) {
            CommentPojo pojoComment = new CommentPojo(com.getOwner().getUserName(), com.getContent(), com.getCreationDate(), com.getIssue().getId());
            pojoComments.add(pojoComment);
        }

        List<Label> labels = issueLabelRepository.getLabelsForIssue(id);

        if (labels.isEmpty()) {
            LOG.log(Level.INFO, "There are no labels for issue " + id);
        }
        for (Label label : labels) {
            LabelPojo pojoLabel = labelService.convertLabelEntityToPojoLabel(label);
            labelPojoList.add(pojoLabel);
        }

        IssuePojo issuePojo = new IssuePojo(issue.getId(), issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(), issue.getUpdateDate(), issue.getLastDate(), issue
                .getState(), pojoComments, labelPojoList);
        if (issue.getAssignee() != null) {
            issuePojo.setAssignee(issue.getAssignee().getUserName());
        }

        if (issue.getAttachments() != null) {
            List<AttachmentPojo> attachments = new ArrayList<AttachmentPojo>();

            for (Attachment attachment : issue.getAttachments()) {
                AttachmentPojo attachmentPojo = new AttachmentPojo();
                attachmentPojo.setIssueId(issue.getId());
                attachmentPojo.setId(attachment.getId());
                attachmentPojo.setFilename(attachment.getFilename());
                attachmentPojo.setFileType(attachment.getContentType());
                attachmentPojo.setContent(attachment.getContent());
                attachments.add(attachmentPojo);
            }

            issuePojo.setAttachments(attachments);
        }
        return issuePojo;

    }

    public List<IssuePojo> getOrderedIssues(int currentPage) {

        List<Issue> issuesListEntity = issueRepository.findOrderedIssues(currentPage);
        List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

        if (issuesListEntity.isEmpty()) {
            LOG.log(Level.INFO, "There are no issues for current page");
        }
        for (int index = 0; index < issuesListEntity.size(); index++) {
            Issue issueEntity = issuesListEntity.get(index);
            IssuePojo issuePojo = new IssuePojo(issueEntity.getId(), issueEntity.getOwner().getUserName(), issueEntity.getTitle(), issueEntity.getContent(), issueEntity
                    .getUpdateDate(), issueEntity.getLastDate(), issueEntity.getState());

            if (issueEntity.getAssignee() != null) {
                issuePojo.setAssignee(issueEntity.getAssignee().getUserName());
            }
            issuesListPojo.add(index, issuePojo);

        }

        return issuesListPojo;
    }

    public int numberOfIssues() {

        return this.issueRepository.numberOfIssues();
    }

    public void assignUserToIssue(Long issueId, UserPojo assignedUser) {

        User assignee = userRepository.findUserByUserName(assignedUser.getUserName());
        Issue issue = issueRepository.findIssue(issueId);
        issue.setAssignee(assignee);

        String x = "\n\nYou became the assignee for the issue :\n\n" + ApplicationParameters.APPLICATION_ROOT + ApplicationParameters.CONTEXT_PATH + "/issues/issue/"
                + issue.getId();
        Email email = new Email();
        email.setTo(issue.getAssignee().getEmail());
        email.setSubject("IssueTracker - AssigneIssue");
        email.setContent(x);

        mh.setUp(email);
        new Thread(mh).start();
        LOG.log(Level.INFO, "Email send to assignee of issue " + issue.getId());

        issueRepository.update(issue);
        LOG.log(Level.INFO, "Issue " + issueId + " was assigned to " + assignee.getUserName());
    }

    public boolean unassignUserToIssue(Long issueId, String username) {
        Issue issue = issueRepository.findIssue(issueId);

        if (issue.getAssignee() != null) {
            String dBIssueUsername = issue.getAssignee().getUserName();

            if (dBIssueUsername.equals(username)) {
                issue.setAssignee(null);
                issueRepository.update(issue);
                LOG.log(Level.INFO, "Issue " + issueId + " is not assigned to anybody");
                return true;
            }

        } else {
            LOG.log(Level.INFO, "Issue " + issueId + " does not have an assignee");
        }
        return false;
    }

}
