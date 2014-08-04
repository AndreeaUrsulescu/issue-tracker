package internship.issuetracker.service;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Email;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.Label;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.pojo.UserPojo;
import internship.issuetracker.repository.IssueLabelRepository;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.UserRepository;
import internship.issuetracker.utils.HTMLParser;
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

	private static final Logger log = Logger.getLogger(IssueService.class.getName());

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IssueLabelRepository issueLabelRepository;

	@Autowired
	private LabelService labelService;

	// @Autowired
	// private MailMail mail;
	@Autowired
	private MailHelper mh;

	public void addIssue(Issue issue) {
		this.issueRepository.create(issue);
		log.log(Level.INFO, "Issue " + issue.getId() + " was created");
	}

	public void updateIssue(Issue issue) {
		Issue newIssue = issueRepository.findIssue(issue.getId());
		newIssue.setContent(issue.getContent());
		newIssue.setTitle(issue.getTitle());
		newIssue.setLastDate(new Date());
		newIssue.setState(issue.getState());

		if (null == newIssue.getAssignee()) {
			log.log(Level.INFO, "Issue " + newIssue.getId() + " doesn't have an assignee , no email send.");

		} else {

			String x = IssueDifference.generateDifference(newIssue, issueRepository.findIssue(issue.getId()));
			Email email = new Email();
			email.setTo(newIssue.getAssignee().getEmail());
			email.setSubject("IssueTracker - UpdateIssue");
			email.setContent(x);

			mh.setUp(email);
			new Thread(mh).start();
			// mail.sendMail(email);
			log.log(Level.INFO, "Email send to assignee of issue " + newIssue.getId());
		}

		this.issueRepository.update(newIssue);
		log.log(Level.INFO, "Issue " + newIssue.getId() + " was updated");
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

		if (labels.size() == 0)
			log.log(Level.INFO, "There are no labels for issue " + id);

		for (Label label : labels) {
			LabelPojo pojoLabel = labelService.convertLabelEntityToPojoLabel(label);
			labelPojoList.add(pojoLabel);
		}

		IssuePojo issuePojo = new IssuePojo(issue.getId(), issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(), issue.getUpdateDate(), issue.getLastDate(), issue
				.getState(), pojoComments, labelPojoList);
		if (issue.getAssignee() != null) {
			issuePojo.setAssignee(issue.getAssignee().getUserName());
		}
		return issuePojo;

	}

	public int getNrOfPages() {
		return this.issueRepository.nrOfPages();
	}

	public List<IssuePojo> getOrderedIssues(int currentPage) {

		List<Issue> issuesListEntity = issueRepository.findOrderedIssues(currentPage);
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		if (issuesListEntity.size() == 0)
			log.log(Level.INFO, "There are no issues for current page");

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(),
					issueEntity.getOwner().getUserName(), issueEntity.getTitle(),
					HTMLParser.convert(issueEntity.getContent()), issueEntity.getUpdateDate(),
					issueEntity.getLastDate(), issueEntity.getState());

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

	public int itemsPerPage() {
		return this.issueRepository.itemsPerPage();
	}

	public void assignUserToIssue(Long issueId, UserPojo assignedUser) {

		User assignee = userRepository.findUserByUserName(assignedUser.getUserName());
		Issue issue = issueRepository.findIssue(issueId);
		issue.setAssignee(assignee);

		String x = "\n\nYou became the assignee for the issue :\n\n" + "http://localhost:8080/issue-tracker/issues/issue/" + issue.getId();
		Email email = new Email();
		email.setTo(issue.getAssignee().getEmail());
		email.setSubject("IssueTracker - AssigneIssue");
		email.setContent(x);

		mh.setUp(email);
		new Thread(mh).start();
		// mail.sendMail(email);
		log.log(Level.INFO, "Email send to assignee of issue " + issue.getId());

		issueRepository.update(issue);
		log.log(Level.INFO, "Issue " + issueId + " was assigned to " + assignee.getUserName());
	}

	public void unassignUserToIssue(Long issueId) {

		Issue issue = issueRepository.findIssue(issueId);
		if (issue.getAssignee() != null) {
			issue.setAssignee(null);
			issueRepository.update(issue);
			log.log(Level.INFO, "Issue " + issueId + " is not assigned to anybody");
		} else
			log.log(Level.INFO, "Issue " + issueId + " does not have an assignee");
	}

}
