package internship.issuetracker.authentication;

import internship.issuetracker.entities.User;
import internship.issuetracker.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationServiceProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUserByUserName(userName);

        if (user == null) {
            throw new BadCredentialsException("Username not found");
        }

        if (!userService.matchPassword(userName, password)) {
            throw new BadCredentialsException("Wrong username or password");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(userName, null, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {

        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
