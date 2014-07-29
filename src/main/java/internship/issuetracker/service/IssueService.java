package internship.issuetracker.service;

import internship.issuetracker.comparators.UpdateDateComparator;
import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.UserRepository;

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
	
	@Autowired
	private UserRepository userRepository;

	public void addIssue(Issue issue) {
		this.issueRepository.create(issue);
	}

	public void updateIssue(Issue issuePojo) {
		Issue issueToUpdate = issueRepository.findIssue(issuePojo.getId());
		issueToUpdate.setContent(issuePojo.getContent());
		issueToUpdate.setTitle(issuePojo.getTitle());
		issueToUpdate.setLastDate(new Date());
		issueToUpdate.setState(issuePojo.getState());
		this.issueRepository.update(issueToUpdate);
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
	
	public IssuePojo getIssue(Long id){
		List<CommentPojo> pojoComments = new ArrayList<CommentPojo>();
		Issue issue = this.issueRepository.findIssue(id);
		
		for (Comment com : issue.getComments()) {
			CommentPojo pojoComment = new CommentPojo(com.getOwner().getUserName(), com.getContent(), com.getCreationDate(), com.getIssue().getId());
			pojoComments.add(pojoComment);
		}
		
		IssuePojo issuePojo = new IssuePojo(issue.getId(), issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(), issue.getUpdateDate(), issue.getLastDate(), issue.getState(), pojoComments);
		return issuePojo;

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
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(),issueEntity.getOwner().getUserName(),issueEntity.getTitle(),issueEntity.getContent(),issueEntity.getUpdateDate(), issueEntity.getLastDate(), issueEntity.getState());
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
