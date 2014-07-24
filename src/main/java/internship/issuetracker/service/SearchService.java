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

		List<Issue> issuesListEntity = searchRepository.findOrderedIssuesByTitle(title, currentPage, orderField, orderType);
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
		return searchRepository.numberOfIssuesByState(state);

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
		List<Issue> issuesListEntity = searchRepository.findOrderedIssuesByState(state, currentPage, orderField, orderType);
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
		return searchRepository.numberOfIssuesByContent(content);
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
		return searchRepository.findOrderedIssuesByContent(content, currentPage, orderField, orderType);
	}
}
