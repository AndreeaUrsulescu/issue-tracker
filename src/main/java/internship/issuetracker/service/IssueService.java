package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.repository.IssueRepository;

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
	
	public Issue getIssue(Long id){
		return this.issueRepository.find(id);
	}

}
