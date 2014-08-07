package internship.issuetracker.service;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Activation;
import internship.issuetracker.repository.ActivationRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActivationServiceTest {
    @Mock
    private ActivationRepository activationRepository;

    @InjectMocks
    private ActivationService activationService = new ActivationService();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddActivation() {
        Activation activation = new Activation();
        activation.setEmail("activation@activation.mock");
        activation.setUserName("userActivation");
        activation.setPassword("activationIsTheAccessPassword");
        activation.encryptPasswordAndKeyHash();
        activationService.addActivation(activation);
        Mockito.verify(activationRepository).create(activation);
    }

    @Test
    public void testGetActivation() {
        Activation activation = new Activation();
        activation.setEmail("activation@activation.mock");
        activation.setUserName("userActivation");
        activation.setPassword("activationIsTheAccessPassword");
        activation.encryptPasswordAndKeyHash();

        Mockito.when(activationRepository.findActivationByKeyHash(activation.getKeyHash())).thenReturn(activation);

        activationService.addActivation(activation);
        Activation activationAux = activationService.getActivation(activation.getKeyHash());
        assertEquals(activation.getKeyHash(), activationAux.getKeyHash());

    }

    @Test
    public void testRemoveActivation() {
        Activation activation = new Activation();
        activation.setEmail("activation@activation.mock");
        activation.setUserName("userActivation");
        activation.setPassword("activationIsTheAccessPassword");
        activation.encryptPasswordAndKeyHash();

        activationService.addActivation(activation);

        activationService.removeActivation(activation);

        Mockito.when(activationRepository.findActivationByKeyHash(activation.getKeyHash())).thenReturn(null);

        activation = activationService.getActivation(activation.getKeyHash());
        assertEquals(null, activation);
    }

}
