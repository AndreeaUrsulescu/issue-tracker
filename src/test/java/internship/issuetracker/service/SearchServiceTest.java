package internship.issuetracker.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.repository.TitleFilter;

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
		
		int actualResult=searchService.numberOfIssues("title", "titlu");
		assertEquals(0, actualResult);
	}

	@Test
	public void testFindOrderedIssues() {

		TitleFilter filter = new TitleFilter("titlu");
		Mockito.when(searchRepository.findOrderedIssues(filter, 0, "Date", "Descending")).thenReturn(new ArrayList<Issue>());
		
		int actualResult= searchService.findOrderedIssues("title", "title", 0, "Date", "Descending").size();
		System.out.println(actualResult);
		assertEquals(0, actualResult);
	}

}
