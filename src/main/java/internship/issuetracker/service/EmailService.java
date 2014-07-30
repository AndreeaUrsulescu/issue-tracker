package internship.issuetracker.service;

import internship.issuetracker.entities.Email;
import internship.issuetracker.repository.EmailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	public void saveEmail(Email email) {
		emailRepository.create(email);
	}
}
