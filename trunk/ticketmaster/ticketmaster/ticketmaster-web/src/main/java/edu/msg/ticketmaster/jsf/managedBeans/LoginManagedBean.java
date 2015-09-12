package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.service.local.UserService;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@ManagedBean(name = "loginManagedBean")

public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = -6149363197770006372L;
	private String username;
	private String password;

	@EJB
	private UserService userService;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		try {
			User user = userService.validate(username, password);
			if (user != null) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);
				session.setAttribute("LoggedUser", user);
				return "home";
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect username or password!",
						"Please insert correct username and password");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "login";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			return "login";
		}
	}

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "home";
	}

}
