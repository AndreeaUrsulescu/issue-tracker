package internship.issuetracker.service;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.MultipleSearchParameter;
import internship.issuetracker.repository.IssueRepository;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private SearchService searchservice = new SearchService();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        user.setUserName("username");
        user.setEmail("email@end.com");
        user.setPassword("parola");
        userRepository.create(user);

        Issue issue = new Issue();
        issue.setTitle("Issue nr 1");
        issue.setOwner(user);
        issue.setContent("Content for issue nr 1");
        issueRepository.create(issue);
    }

    @Test
    public void testMultiplePredicates() {
        MultipleSearchParameter multipleSearchParameter = new MultipleSearchParameter();
        multipleSearchParameter.setTitle("issue");
        multipleSearchParameter.setContent("content");
        assert (searchservice.multiplePredicates(multipleSearchParameter).isEmpty() == false);
    }

    @Test
    public void testNumberOfIssuesMultipleSearch() {
        MultipleSearchParameter multipleSearchParameter = new MultipleSearchParameter();
        multipleSearchParameter.setTitle("issue");
        multipleSearchParameter.setContent("content");
        assert (searchservice.numberOfIssuesMultipleSearch(multipleSearchParameter) > 0);
    }

}
