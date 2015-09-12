package edu.msg.ticketmaster.jsf.managedBeans;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.msg.ticketmaster.backend.model.User;

@ManagedBean
public class HomeBean {
	
	private User loggedUser;
	private String userType="VIEWER";
	
	@PostConstruct
    public void init() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		loggedUser = (User) session.getAttribute("LoggedUser");
		if(loggedUser!=null) {
			userType = loggedUser.getUserType().toString();
		}	
    }
	
	public boolean isManagementButtonRendered() {
		
		if(userType.equals("ADMIN") || userType.equals("MANAGER")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isProfileButtonRendered() {
		
		if(userType.equals("ADMIN") || userType.equals("CLIENT")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isUserLogged() {
		
		if(loggedUser!=null) {
			return true;
		}
		else {
			return false;
		}
	}

}
