package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Date;
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

	public List<Issue> getIssuesByTitle(String title) {
		return this.issueRepository.findIssuesByTitle(title);

	}

	public List<Issue> getIssuesByDate(Date date) {
		return this.issueRepository.findIssuesByDate(date);
	}

	public List<Issue> getIssues(){
	    return this.issueRepository.findIssues();
	}
	
	public Issue getIssue(Long id){
		return this.issueRepository.findIssue(id);

	}	
	
	public List<Issue> getIssuesForPagination(int page){
		return this.issueRepository.findIssuesForPagination(page);
	}

	public int getNrOfPages(){
		return this.issueRepository.nrOfPages();
	}
	
	public List<IssuePojo> getOrderedIssues(int currentPage) {
		
		List<Issue> issuesListEntity =  issueRepository.findOrderedIssues(currentPage);
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();
		
		for(int index = 0 ;index < issuesListEntity.size() ; index++ ){
			Issue issueEntity  = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(),issueEntity.getOwner().getUserName(),issueEntity.getTitle(),issueEntity.getContent(),issueEntity.getUpdateDate(),issueEntity.getState());
		    issuesListPojo.add(index,issuePojo);
			
		}
		
		return issuesListPojo;
	}
	
    public int numberOfIssues(){
		
		return this.issueRepository.numberOfIssues();
	}
    
    public int itemsPerPage(){
		return this.issueRepository.itemsPerPage();
	}
}
