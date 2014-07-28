package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.enums.State;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.ContentFilter;
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

	public int numberOfIssuesByTitle(String title) {
		TitleFilter titleFilter = new TitleFilter(title);
		return searchRepository.numberOfIssues(titleFilter);

	}

	public List<IssuePojo> findOrderedIssuesByTitle(String title, int currentPage) {
		return findOrderedIssuesByTitleAux(title, currentPage, "updateDate", "desc");
	}

	public List<IssuePojo> findOrderedIssuesByTitle(String title, int currentPage, String orderField) {
		return findOrderedIssuesByTitleAux(title, currentPage, orderField, "desc");
	}

	public List<IssuePojo> findOrderedIssuesByTitle(String title, int currentPage, String orderField, String orderType) {
		return findOrderedIssuesByTitleAux(title, currentPage, orderField, orderType);
	}

	public List<IssuePojo> findOrderedIssuesByTitleAux(String title, int currentPage, String orderField, String orderType) {
		TitleFilter titleFilter = new TitleFilter(title);
		List<Issue> issuesListEntity = searchRepository.findOrderedIssues(titleFilter, currentPage, orderField, orderType);
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(), issueEntity.getOwner().getUserName(), issueEntity.getTitle(), issueEntity.getContent(), issueEntity
					.getUpdateDate(), issueEntity.getState());
			issuesListPojo.add(index, issuePojo);

		}
		return issuesListPojo;
	}

	public int numberOfIssuesByState(State state) {
		StateFilter stateFilter = new StateFilter(state);
		return searchRepository.numberOfIssues(stateFilter);

	}

	public List<IssuePojo> findOrderedIssuesByState(State state, int currentPage) {
		return findOrderedIssuesByStateAux(state, currentPage, "updateDate", "desc");
	}

	public List<IssuePojo> findOrderedIssuesByState(State state, int currentPage, String orderField) {
		return findOrderedIssuesByStateAux(state, currentPage, orderField, "desc");
	}

	public List<IssuePojo> findOrderedIssuesByState(State state, int currentPage, String orderField, String orderType) {
		return findOrderedIssuesByStateAux(state, currentPage, orderField, orderType);
	}

	public List<IssuePojo> findOrderedIssuesByStateAux(State state, int currentPage, String orderField, String orderType) {
		StateFilter stateFilter = new StateFilter(state);
		List<Issue> issuesListEntity = searchRepository.findOrderedIssues(stateFilter, currentPage, orderField, orderType);
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(), issueEntity.getOwner().getUserName(), issueEntity.getTitle(), issueEntity.getContent(), issueEntity
					.getUpdateDate(), issueEntity.getState());
			issuesListPojo.add(index, issuePojo);

		}
		return issuesListPojo;

	}

	
	public int numberOfIssuesByContent(String content) {
		ContentFilter contentFilter = new ContentFilter(content);
		return searchRepository.numberOfIssues(contentFilter);
	}

	public List<IssuePojo> findOrderedIssuesByContent(String content, int currentPage) {
		return findOrderedIssuesByContentAux(content, currentPage, "updateDate", "desc");
	}

	public List<IssuePojo> findOrderedIssuesByContent(String content, int currentPage, String orderField) {
		return findOrderedIssuesByContentAux(content, currentPage, orderField, "desc");
	}

	public List<IssuePojo> findOrderedIssuesByContent(String content, int currentPage, String orderField, String orderType) {
		return findOrderedIssuesByContentAux(content, currentPage, orderField, orderType);
	}

	public List<IssuePojo> findOrderedIssuesByContentAux(String content, int currentPage, String orderField, String orderType) {
		ContentFilter contentFilter = new ContentFilter(content);
		List<Issue> issuesListEntity = searchRepository.findOrderedIssues(contentFilter, currentPage, orderField, orderType);
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(), issueEntity.getOwner().getUserName(), issueEntity.getTitle(), issueEntity.getContent(), issueEntity
					.getUpdateDate(), issueEntity.getState());
			issuesListPojo.add(index, issuePojo);

		}
		return issuesListPojo;
	}


}
