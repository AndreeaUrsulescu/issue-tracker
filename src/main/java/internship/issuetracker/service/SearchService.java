package internship.issuetracker.service;

import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchService {
    
    @Autowired
    private SearchRepository searchRepository;
    
    public int numberOfIssues(String title) {
	return searchRepository.numberOfIssues(title);
	
    }
    public List<Issue> findOrderedIssues(String title, int currentPage) {
	return searchRepository.findOrderedIssues(title, currentPage);
    }

}
