package internship.issuetracker.service;


import internship.issuetracker.entities.User;
import internship.issuetracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository ;
	
	@Autowired
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public void addUser(User user){
		this.userRepository.create(user);
	}
}

