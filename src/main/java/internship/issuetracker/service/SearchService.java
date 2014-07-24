package internship.issuetracker.service;

import java.util.List;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    
    @Autowired
    private SearchRepository searchRepository;
    
    public int numberOfIssues(String title) {
	return searchRepository.numberOfIssues(title);
	
    }
    public List<Issue> findOrderedIssues(String title, int currentPage) {
	return searchRepository.findOrderedIssues(title, currentPage);
    }
    
    public List<IssuePojo> findIssuesByContent(String content, int currentPage) {
    	return searchRepository.findIssuesByContent(content, currentPage);
    }

}
