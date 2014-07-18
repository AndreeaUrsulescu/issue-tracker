package internship.issuetracker.authentication;

import java.util.ArrayList;
import java.util.List;

import internship.issuetracker.entities.User;
import internship.issuetracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationServiceProvider implements AuthenticationProvider{

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		User user ;
		
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		
		user = userService.findUserByUserName(userName);
		
		if(user == null ){
			throw new BadCredentialsException("UserName not found");
		}
		
		if (userService.matchPassword(userName, password) == false ){
			throw new BadCredentialsException("Wrong password");
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		return new UsernamePasswordAuthenticationToken(userName,password,grantedAuthorities);
	}

	
	@Override
	public boolean supports(Class<? extends Object> authentication) {
		
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
