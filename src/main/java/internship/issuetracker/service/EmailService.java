package internship.issuetracker.service;

import internship.issuetracker.entities.Email;
import internship.issuetracker.repository.EmailRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger LOG = Logger.getLogger(EmailService.class.getName());

    @Autowired
    private EmailRepository emailRepository;

    public void saveEmail(Email email) {
        emailRepository.create(email);
        LOG.log(Level.INFO, "An email was saved for " + email.getTo());
    }
}
