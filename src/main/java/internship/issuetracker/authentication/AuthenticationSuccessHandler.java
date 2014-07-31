package internship.issuetracker.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import internship.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

public class AuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		String username = authentication.getName();
		request.getSession().setAttribute("isLoggedIn", true);
		request.getSession().setAttribute("user",userService.findUserByUserName(username));
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		return "/issues";
	}
}
