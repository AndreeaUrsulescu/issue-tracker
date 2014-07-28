package internship.issuetracker.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)

public class LabelServiceTest {
	@Mock
	private LabelRepository labelRepository;
	
	@InjectMocks
	private LabelService labelService=new LabelService();
	
	@Before	
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAssignLabelToIssue()
	{
		Label label=new Label();
	}
}
