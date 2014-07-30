package internship.issuetracker.service;

import internship.issuetracker.entities.Activation;
import internship.issuetracker.repository.ActivationRepository;
import internship.issuetracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ActivationService {

	@Autowired
	private ActivationRepository activationRepository;
	
	public void addActivation(Activation activation)
	{
		this.activationRepository.create(activation);
	}
	
	public Activation getActivation(String keyHash)
	{
		return activationRepository.findActivationByKeyHash(keyHash);
	}
	
	public void removeActivation(Activation activation)
	{
		this.activationRepository.remove(activation);
	}
	
	
	
}
