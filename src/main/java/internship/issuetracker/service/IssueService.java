package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.repository.IssueRepository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
	@Autowired
	private IssueRepository issueRepository;

	public void addIssue(Issue issue) {
		this.issueRepository.create(issue);
	}

	public void updateIssue(Issue issue) {
		this.issueRepository.update(issue);
	}

	public List<Issue> getIssues(String title) {
		return this.issueRepository.findTitle(title);
	}

	public List<Issue> getIssues(Date date) {
		return this.issueRepository.findTitle(date);
	}

	public Issue getIssue(Long id){
	    return this.issueRepository.findIssue(id);
	}
}
