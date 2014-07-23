package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.utils.XSSescape;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
	@Autowired
	private IssueRepository issueRepository;

	public void addIssue(Issue issue) {
		issue.setTitle(XSSescape.revert(issue.getTitle()));
		issue.setContent(XSSescape.revert(issue.getContent()));
		this.issueRepository.create(issue);
	}

	public void updateIssue(Issue issue) {
		issue.setTitle(XSSescape.revert(issue.getTitle()));
		issue.setContent(XSSescape.revert(issue.getContent()));
		this.issueRepository.update(issue);
	}

	private List<Issue> escape(List<Issue> issuesList){
		for (Issue issue : issuesList) {
			issue.setTitle(XSSescape.convert(issue.getTitle()));
			issue.setContent(XSSescape.convert(issue.getContent()));			
			}			
		return issuesList;
	}
	public List<Issue> getIssuesByTitle(String title) {
		return escape(this.issueRepository.findIssuesByTitle(title));

	}

	public List<Issue> getIssuesByDate(Date date) {
		return escape(this.issueRepository.findIssuesByDate(date));
	}

	public List<Issue> getIssues(){
	    return escape(this.issueRepository.findIssues());
	}
	
	public Issue getIssue(Long id){
		Issue issue = this.issueRepository.findIssue(id);
		issue.setTitle(XSSescape.convert(issue.getTitle()));
		issue.setContent(XSSescape.convert(issue.getContent()));			
	    return issue;
	}	
	
	public List<Issue> getIssuesForPagination(int page){
		return escape(this.issueRepository.findIssuesForPagination(page));
	}

	public int getNrOfPages(){
		return this.issueRepository.nrOfPages();
	}
	
	public List<Issue> getOrderedIssues() {
		return escape(this.issueRepository.findOrderedIssues());
	}
}
