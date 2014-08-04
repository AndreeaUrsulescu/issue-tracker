package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.MultipleSearchParameter;
import internship.issuetracker.repository.LabelRepository;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.utils.HTMLParser;

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
	
	@Autowired
	private LabelRepository labelRepository;

	private List<IssuePojo> entityToPojo(List<Issue> issuesListEntity) {
		List<IssuePojo> issuesListPojo = new ArrayList<IssuePojo>();

		for (int index = 0; index < issuesListEntity.size(); index++) {
			Issue issueEntity = issuesListEntity.get(index);
			IssuePojo issuePojo = new IssuePojo(issueEntity.getId(),
					issueEntity.getOwner().getUserName(),
					issueEntity.getTitle(), HTMLParser.convert(issueEntity.getContent()),
					issueEntity.getUpdateDate(), issueEntity.getLastDate(),
					issueEntity.getState());
			if(null != issueEntity.getAssignee()){
			     issuePojo.setAssignee(issueEntity.getAssignee().getUserName());
			}
			issuesListPojo.add(index, issuePojo);

		}
		return issuesListPojo;
	}

	public List<IssuePojo> multiplePredicates(MultipleSearchParameter searchParameters) {

		List<Issue> issuesListEntity = searchRepository.multiplePredicates(searchParameters);

		if (issuesListEntity.size() == 0){
			log.log(Level.INFO,	"There are no issues for the given search criteria");
		}

		return entityToPojo(issuesListEntity);
	}
	
	public int numberOfIssuesMultipleSearch(MultipleSearchParameter searchParameters) {

		int listSize = searchRepository.numberOfIssuesMultipleSearch(searchParameters);
		return listSize;
	}

}
