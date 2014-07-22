package internship.issuetracker.service;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.utils.EncryptData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {

	@Mock
	private IssueRepository issueRepository;

	@InjectMocks
	private IssueService issueService = new IssueService();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddIssue() {
		Issue issue = new Issue();
		issue.setTitle("Issue nr 1");
		issue.setContent("Content for issue nr 1");

		issueService.addIssue(issue);
		Mockito.verify(issueRepository).create(issue);

	}
	
	@Test
	public void tesGetNrOftPages() {
		Mockito.when(issueRepository.nrOfPages()).thenReturn(2);
		int actualResult = issueService.getNrOfPages();
		assertEquals(2, actualResult);
	}


}
