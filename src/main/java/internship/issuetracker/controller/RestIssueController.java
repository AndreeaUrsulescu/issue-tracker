package internship.issuetracker.controller;

import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.SearchParameter;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.SearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/issues")
public class RestIssueController {

	@Autowired
	private IssueService issueService;
	
	@Autowired 
	private SearchService searchService;
	
	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	@ResponseBody
	public List<IssuePojo> viewIssuesPage(@PathVariable("pageNumber") Integer pageNumber) {
		
		List<IssuePojo> issuesListPojo =  issueService.getOrderedIssues(pageNumber);
		return issuesListPojo;
	}
	
	@RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> search(@ModelAttribute SearchParameter searchParameters) {
		
		String searchCriteria = searchParameters.getSearchCriteria();
		int pageNumber = searchParameters.getPageNumber();
		String sortCriteria = searchParameters.getSortCriteria();
		String sortType = searchParameters.getSortType();
		
		List<IssuePojo> resultList = null;
		Map<String, Object> map = new HashMap<String,Object>();
		if(searchCriteria.equals("title")){
			resultList = searchService.findOrderedIssuesByTitle(searchParameters.getInput(), pageNumber,sortCriteria,sortType);
			map.put("listLength",searchService.numberOfIssuesByTitle(searchParameters.getInput()));
			}
		else if (searchCriteria.equals("content")){
			resultList = searchService.findOrderedIssuesByContent(searchParameters.getInput(), pageNumber,sortCriteria,sortType);
			map.put("listLength",searchService.numberOfIssuesByContent(searchParameters.getInput()));
			}
		else if (searchCriteria.equals("state")){
			resultList = searchService.findOrderedIssuesByState(searchParameters.getState(), pageNumber,sortCriteria,sortType);
			map.put("listLength",searchService.numberOfIssuesByState(searchParameters.getState()));
			}
		map.put("issuesList", resultList);
		map.put("issuesPerPage",SearchRepository.itemsPerPage );
		
		return map;
	}
	
}
