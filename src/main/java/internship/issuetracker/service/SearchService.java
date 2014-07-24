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

	public List<IssuePojo> findOrderedIssuesByTitle(String title,
			int currentPage) {

		List<Issue> issuesListEntity = searchRepository
				.findOrderedIssuesByTitle(title, currentPage);
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

	public int numberOfIssuesByState(State state) {
		return searchRepository.numberOfIssuesByState(state);

	}

	public List<IssuePojo> findOrderedIssuesByState(State state, int currentPage) {
		List<Issue> issuesListEntity = searchRepository
				.findOrderedIssuesByState(state, currentPage);
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

	public int numberOfIssuesByContent(String content) {
		return searchRepository.numberOfIssuesByContent(content);
	}

	public List<IssuePojo> findOrderedIssuesByContent(String content,
			int currentPage) {
		return searchRepository
				.findOrderedIssuesByContent(content, currentPage);
	}
}
