package internship.issuetracker.service;


import internship.issuetracker.entities.User;
import internship.issuetracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository ;

	public void addUser(User user){
		this.userRepository.create(user);
	}
	
	
	public void updateUser(User user){
		this.userRepository.update(user);
	}
		
	public boolean exists(String userName){
		return this.userRepository.exists(userName);
	}
}

