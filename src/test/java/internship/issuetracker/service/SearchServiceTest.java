package internship.issuetracker.service;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.filters.TitleFilter;
import internship.issuetracker.pojo.SearchParameter;
import internship.issuetracker.repository.SearchRepository;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

	@Mock
	private SearchRepository searchRepository;
	
	@Mock
	private IssueService issueService;

	@InjectMocks
	private SearchService searchService = new SearchService();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testNumberOfIssues() {

		TitleFilter filter = new TitleFilter("titlu");
		Mockito.when(searchRepository.numberOfIssues(filter)).thenReturn(0);
		SearchParameter searchParameter=new SearchParameter();
		searchParameter.setPageNumber(0);
		searchParameter.setInput("titlu");
		searchParameter.setSearchCriteria("title");
		searchParameter.setSortCriteria("Date");
		searchParameter.setSortType("Descending");
		
		int actualResult=searchService.numberOfIssues(searchParameter);
		assertEquals(0, actualResult);
	}

	@Test
	public void testFindOrderedIssues() {

		TitleFilter filter = new TitleFilter("titlu");
		Mockito.when(searchRepository.findOrderedIssues(filter, 0, "Date", "Descending")).thenReturn(new ArrayList<Issue>());
		SearchParameter searchParameter=new SearchParameter();
		searchParameter.setPageNumber(0);
		searchParameter.setInput("titlu");
		searchParameter.setSearchCriteria("title");
		searchParameter.setSortCriteria("Date");
		searchParameter.setSortType("Descending");
		
		int actualResult= searchService.findOrderedIssues(searchParameter).size();
		assertEquals(0, actualResult);
	}

}
