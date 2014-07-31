package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.filters.ContentFilter;
import internship.issuetracker.filters.SearchFilterInt;
import internship.issuetracker.filters.StateFilter;
import internship.issuetracker.filters.TitleFilter;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.MultipleSearchParameter;
import internship.issuetracker.pojo.SearchParameter;
import internship.issuetracker.repository.SearchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	private static final Logger log = Logger.getLogger(SearchService.class
			.getName());

	@Autowired
	private SearchRepository searchRepository;

	private SearchFilterInt<Issue> getFilter(String searchCriteria,
			SearchParameter searchParameters) {

		SearchFilterInt<Issue> filter = null;
		if (searchCriteria.equals("title")) {
			filter = new TitleFilter(searchParameters.getInput());
		} else if (searchCriteria.equals("content")) {
			filter = new ContentFilter(searchParameters.getInput());
		} else if (searchCriteria.equals("state")) {
			filter = new StateFilter(searchParameters.getState());
		}
		return filter;
	}

	private List<IssuePojo> entityToPojo(List<Issue> issuesListEntity) {
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(),
					issueEntity.getOwner().getUserName(),
					issueEntity.getTitle(), issueEntity.getContent(),
					issueEntity.getUpdateDate(), issueEntity.getLastDate(),
					issueEntity.getState());
			issuesListPojo.add(index, issuePojo);

		}
		return issuesListPojo;
	}

	public int numberOfIssues(SearchParameter searchParameters) {
		String searchCriteria = searchParameters.getSearchCriteria();
		SearchFilterInt<Issue> filter = this.getFilter(searchCriteria,
				searchParameters);
		return searchRepository.numberOfIssues(filter);
	}

	public List<IssuePojo> findOrderedIssues(SearchParameter searchParameters) {

		String searchCriteria = searchParameters.getSearchCriteria();
		SearchFilterInt<Issue> filter = this.getFilter(searchCriteria,
				searchParameters);

		List<Issue> issuesListEntity = searchRepository.findOrderedIssues(
				filter, searchParameters.getPageNumber(),
				searchParameters.getSortCriteria(),
				searchParameters.getSortType());

		if (issuesListEntity.size() == 0)
			log.log(Level.INFO,
					"There are no issues for the given search criteria");

		return entityToPojo(issuesListEntity);
	}
	
	public List<IssuePojo> multiplePredicates(MultipleSearchParameter searchParameters) {

		List<Issue> issuesListEntity = searchRepository.multiplePredicates(searchParameters);

		if (issuesListEntity.size() == 0)
			log.log(Level.INFO,
					"There are no issues for the given search criteria");

		return entityToPojo(issuesListEntity);
	}
	
	public int numberOfIssuesMultipleSearch(MultipleSearchParameter searchParameters) {

		int listSize = searchRepository.numberOfIssuesMultipleSearch(searchParameters);
		return listSize;
	}

}
