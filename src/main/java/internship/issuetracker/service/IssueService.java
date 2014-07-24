package internship.issuetracker.service;

import internship.issuetracker.comparators.UpdateDateComparator;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    
    public List<Issue> SortByUpdateDate(List<Issue> issues,String order)
    {
    	
    	if(order.equals("descending"))           // you have to specify that the order is descending, otherwise it will be ascending
    	{
    		Comparator<Issue> reverse=Collections.reverseOrder(new UpdateDateComparator());
    		Collections.sort(issues, reverse);
    		return issues;
    	}
    	
    	Collections.sort(issues,new UpdateDateComparator());
       	return issues;
    }
}
