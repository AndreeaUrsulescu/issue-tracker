package internship.issuetracker.service;

import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.ResetPasswordRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResetPasswordService {

	private static final Logger log = Logger.getLogger(ActivationService.class.getName());

	@Autowired
	private ResetPasswordRepository resetPasswordRepository;

	public void addResetPassword(ResetPassword resetPassword) {
		this.resetPasswordRepository.create(resetPassword);
		log.log(Level.INFO, "Activation entity was persisted");
	}

	public ResetPassword getResetPassword(String keyHash) {
		return resetPasswordRepository.findResetPasswordByKeyHash(keyHash);
	}	

	public boolean existsResetPasswordForUser(User user){
		return resetPasswordRepository.exists(user);
	}
	
	public void removeResetPassword(ResetPassword resetPassword) {
		this.resetPasswordRepository.remove(resetPassword);
		log.log(Level.INFO, "Activation entity was removed");
	}
}
