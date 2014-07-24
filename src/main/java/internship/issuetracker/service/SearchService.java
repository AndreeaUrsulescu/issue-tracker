package internship.issuetracker.service;

import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.enums.State;
import internship.issuetracker.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;

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

}
