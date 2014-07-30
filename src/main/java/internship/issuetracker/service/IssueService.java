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
import internship.issuetracker.repository.LabelRepository;
import internship.issuetracker.repository.UserRepository;
import internship.issuetracker.utils.IssueDifference;
import internship.issuetracker.utils.MailMail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IssueLabelRepository issueLabelRepository;
	
	@Autowired
	private LabelService labelService;

	@Autowired
	private MailMail mail;
	
	public void addIssue(Issue issue) {
		this.issueRepository.create(issue);
	}

	public void updateIssue(Issue issuePojo) {
		Issue issueToUpdate = issueRepository.findIssue(issuePojo.getId());				
		issueToUpdate.setContent(issuePojo.getContent());
		issueToUpdate.setTitle(issuePojo.getTitle());
		issueToUpdate.setLastDate(new Date());
		issueToUpdate.setState(issuePojo.getState());
			
		String x=IssueDifference.generateDifference(issueToUpdate, issueRepository.findIssue(issuePojo.getId()));		
		Email email=new Email();
		email.setTo(issueToUpdate.getAssignee().getEmail());
		email.setSubject("IssueTracker - UpdateIssue");
		email.setContent(x);
		mail.sendMail(email);
		
		this.issueRepository.update(issueToUpdate);
	}

	public IssuePojo getIssue(Long id) {
		List<CommentPojo> pojoComments = new ArrayList<CommentPojo>();
		List<LabelPojo> labelPojoList = new ArrayList<LabelPojo>();
		Issue issue = this.issueRepository.findIssue(id);

		for (Comment com : issue.getComments()) {
			CommentPojo pojoComment = new CommentPojo(com.getOwner()
					.getUserName(), com.getContent(), com.getCreationDate(),
					com.getIssue().getId());
			pojoComments.add(pojoComment);
		}

		List<Label> labels = issueLabelRepository.getLabelsForIssue(id);

		for (Label label : labels) {
			LabelPojo pojoLabel = labelService.convertLabelEntityToPojoLabel(label);
			labelPojoList.add(pojoLabel);
		}

		IssuePojo issuePojo = new IssuePojo(issue.getId(), issue.getOwner()
				.getUserName(), issue.getTitle(), issue.getContent(),
				issue.getUpdateDate(), issue.getLastDate(), issue.getState(),
				pojoComments, labelPojoList);
		if (issue.getAssignee() != null) {
			issuePojo.setAssignee(issue.getAssignee().getUserName());
		}
		return issuePojo;

	}

	public int getNrOfPages() {
		return this.issueRepository.nrOfPages();
	}

	public List<IssuePojo> getOrderedIssues(int currentPage) {

		List<Issue> issuesListEntity = issueRepository
				.findOrderedIssues(currentPage);
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(),
					issueEntity.getOwner().getUserName(),
					issueEntity.getTitle(), issueEntity.getContent(),
					issueEntity.getUpdateDate(), issueEntity.getLastDate(),
					issueEntity.getState());

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

		User assignee = userRepository.findUserByUserName(assignedUser
				.getUserName());
		Issue issue = issueRepository.findIssue(issueId);
		issue.setAssignee(assignee);
		issueRepository.update(issue);
	}
	
	public void unassignUserToIssue(Long issueId) {

		Issue issue = issueRepository.findIssue(issueId);
		if (issue.getAssignee() != null){
			issue.setAssignee(null);
			issueRepository.update(issue);
		}
	}

}
