package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.enums.State;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.ContentFilter;
import internship.issuetracker.repository.SearchFilterInt;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.repository.StateFilter;
import internship.issuetracker.repository.TitleFilter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	@Autowired
	private SearchRepository searchRepository;

	private List<IssuePojo> entityToPojo(List<Issue> issuesListEntity) {
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(), issueEntity.getOwner().getUserName(), issueEntity.getTitle(), issueEntity.getContent(), issueEntity
					.getUpdateDate(), issueEntity.getState());
			issuesListPojo.add(index, issuePojo);

		}
		return issuesListPojo;
	}

	public int numberOfIssues(String searchCriteria,Object obj) {
		SearchFilterInt<Issue> filter = null;
		if(searchCriteria.equals("title")){
			filter = new TitleFilter((String)obj);
			}
		else if (searchCriteria.equals("content")){
			filter = new ContentFilter((String)obj);
			}
		else if (searchCriteria.equals("state")){
			filter = new StateFilter((State)obj);
			}	
		return searchRepository.numberOfIssues(filter);
	}

	public List<IssuePojo> findOrderedIssues(String searchCriteria, Object obj, int currentPage, String orderField, String orderType) {
		
		SearchFilterInt<Issue> filter = null;
		if(searchCriteria.equals("title")){
			filter = new TitleFilter((String)obj);
			}
		else if (searchCriteria.equals("content")){
			filter = new ContentFilter((String)obj);
			}
		else if (searchCriteria.equals("state")){
			filter = new StateFilter((State)obj);
			}		
		
		List<Issue> issuesListEntity = searchRepository.findOrderedIssues(filter, currentPage, orderField, orderType);

		return entityToPojo(issuesListEntity);
	}


}
