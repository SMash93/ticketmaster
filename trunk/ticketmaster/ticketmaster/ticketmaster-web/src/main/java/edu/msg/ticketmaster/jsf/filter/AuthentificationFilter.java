package edu.msg.ticketmaster.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.model.UserType;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthentificationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession sess = req.getSession(false);

		String requestUrl = req.getRequestURI();
		boolean isLoginPage = requestUrl.contains("/pages/login.xhtml");
		boolean isUserLoggedIn = sess != null && sess.getAttribute("LoggedUser") != null;
		boolean isResource = requestUrl.contains("javax.faces.resource");
		boolean isUnregistered = requestUrl.contains("/pages/");

		if (!isUserLoggedIn && !isResource && !isLoginPage && !isUnregistered) {
			resp.sendRedirect(req.getContextPath() + "/pages/login.xhtml");
			return;
		}

		if (isUserLoggedIn) {
			User loggedUser = (User) sess.getAttribute("LoggedUser");
			String userType = loggedUser.getUserType().toString();

			boolean requestManagerAdmin = requestUrl.contains("/pages/management.xhtml");

			if (requestManagerAdmin) {
				if (!userType.equalsIgnoreCase(UserType.ADMIN.toString())
						&& !userType.equalsIgnoreCase(UserType.MANAGER.toString())) {
					resp.sendRedirect(req.getContextPath() + "/pages/home.xhtml");
					return;
				}
			}

		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
