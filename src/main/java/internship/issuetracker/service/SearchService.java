package internship.issuetracker.service;

import java.util.ArrayList;
import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.enums.State;
import internship.issuetracker.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    
    @Autowired
    private SearchRepository searchRepository;
    
    public int numberOfIssuesByTitle(String title) {
	return searchRepository.numberOfIssuesByTitle(title);
	
    }
    public List<Issue> findOrderedIssuesByTitle(String title, int currentPage) {
	return searchRepository.findOrderedIssuesByTitle(title, currentPage);
    }
    
    public int numberOfIssuesByState(State state) {
	return searchRepository.numberOfIssuesByState(state);
	
    }
    public List<Issue> findOrderedIssuesByState(State state, int currentPage) {
	return searchRepository.findOrderedIssuesByState(state, currentPage);
    }
	public int numberOfIssuesByContent(String content) {
		return searchRepository.numberOfIssuesByContent(content);
	}

	public List<IssuePojo> findOrderedIssues(String title, int currentPage) {

		List<Issue> issuesListEntity = searchRepository.findOrderedIssues(title, currentPage);
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(),
					issueEntity.getOwner().getUserName(),
					issueEntity.getTitle(), issueEntity.getContent(),
					issueEntity.getUpdateDate(), issueEntity.getState());
			issuesListPojo.add(index, issuePojo);

		}
		return issuesListPojo;
	}
}
