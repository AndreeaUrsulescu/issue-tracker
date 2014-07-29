package internship.issuetracker.service;

import filters.ContentFilter;
import filters.SearchFilterInt;
import filters.StateFilter;
import filters.TitleFilter;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.SearchParameter;
import internship.issuetracker.repository.SearchRepository;

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

	public int numberOfIssues(SearchParameter searchParameters) {
		SearchFilterInt<Issue> filter = null;
		String searchCriteria = searchParameters.getSearchCriteria();
		if(searchCriteria.equals("title")){
			filter = new TitleFilter(searchParameters.getInput());
			}
		else if (searchCriteria.equals("content")){
			filter = new ContentFilter(searchParameters.getInput());
			}
		else if (searchCriteria.equals("state")){
			filter = new StateFilter(searchParameters.getState());
			}	
		return searchRepository.numberOfIssues(filter);
	}

	public List<IssuePojo> findOrderedIssues(SearchParameter searchParameters) {
			
		String searchCriteria = searchParameters.getSearchCriteria();
		SearchFilterInt<Issue> filter = null;
		if(searchCriteria.equals("title")){
			filter = new TitleFilter(searchParameters.getInput());
			}
		else if (searchCriteria.equals("content")){
			filter = new ContentFilter(searchParameters.getInput());
			}
		else if (searchCriteria.equals("state")){
			filter = new StateFilter(searchParameters.getState());
			}		
		
		List<Issue> issuesListEntity = searchRepository.findOrderedIssues(filter, searchParameters.getPageNumber(), searchParameters.getSortCriteria(), searchParameters.getSortType());

		return entityToPojo(issuesListEntity);
	}


}
