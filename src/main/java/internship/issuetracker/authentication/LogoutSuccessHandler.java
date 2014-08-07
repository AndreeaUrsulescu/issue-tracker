package internship.issuetracker.authentication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    private static final Logger LOG = Logger.getLogger(LogoutSuccessHandler.class.getName());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LOG.log(Level.INFO, "User logged out succesfully");
        SecurityContextHolder.clearContext();
        request.getSession().setAttribute("user", null);
        super.onLogoutSuccess(request, response, null);
    }
}