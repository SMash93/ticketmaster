package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.service.local.UserService;

@ManagedBean(name = "signUpBean")
public class SignUpBean implements Serializable {

	private static final long serialVersionUID = -9181917368978532243L;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phone;
	private FacesMessage message = null;

	@EJB
	private UserService userService;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String signUp() {

		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setAddress(address);
		user.setPhoneNumber(phone);

		try {

			if (userService.signUp(user))
				return "movies";

		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exists!", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "signup";
	}

}
