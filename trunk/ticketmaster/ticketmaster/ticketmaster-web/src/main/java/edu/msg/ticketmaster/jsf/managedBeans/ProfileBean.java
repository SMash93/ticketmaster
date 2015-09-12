package edu.msg.ticketmaster.jsf.managedBeans;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.model.UserType;
import edu.msg.ticketmaster.backend.service.local.UserService;

@ManagedBean
@RequestScoped
public class ProfileBean implements Serializable {

	private static final long serialVersionUID = -9181917368978532243L;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phone;
	private String type; 
	private Map<String,String> types;
	private List<User> users;
	private User selectedUser;
	private String userType="USER";
	private FacesMessage message = null;

	@EJB
	private UserService userService;
	
	
	@PostConstruct
    public void init() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		User loggedUser = (User) session.getAttribute("LoggedUser");
		userType = loggedUser.getUserType().toString();
		
		System.out.println("usertype from session: "+userType);
		
		users = userService.findAllUsers();
		types  = new HashMap<String,String>();
		types.put("ADMIN", "ADMIN");
		types.put("MANAGER", "MANAGER");
		types.put("CASHIER", "CASHIER");
		types.put("CLIENT", "CLIENT");
		
    }
 

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Map<String, String> getTypes() {
        return types;
    }
	
	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public User getSelectedUser() {
        return selectedUser;
    }
 
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }


	public void create() {
	  
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserType(UserType.valueOf(type));
		user.setAddress(address);
		user.setPhoneNumber(phone);	
		userService.insertUser(user);
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes! User inserted succesfully!","");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	public void update() {
		  
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserType(UserType.ADMIN);
		user.setAddress(address);
		user.setPhoneNumber(phone);
		user.setUserType(UserType.valueOf(type));
		user.setId(userService.findByUsername(user).getId());		
		userService.updateUser(user);
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes! User updated succesfully!","");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void delete() {
		  
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserType(UserType.ADMIN);
		user.setAddress(address);
		user.setPhoneNumber(phone);
		user.setUserType(UserType.valueOf(type));
		user.setId(userService.findByUsername(user).getId());
		userService.deleteUser(user);
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes! User deleted succesfully!","");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	
  
	public boolean isCommandButtonRendered() {
	  
		  if(userType.equals("ADMIN")) {
			  return true;
		  }
		  else {
			  return false;
		  }
	  
	}
	
	public void completeFields() {
		
		firstname = getSelectedUser().getFirstName();
		lastname = getSelectedUser().getLastName();
		username = getSelectedUser().getUsername();
		password = getSelectedUser().getPassword();
		email = getSelectedUser().getPassword();
		type = getSelectedUser().getUserType().toString();
		address = getSelectedUser().getAddress();
		phone = getSelectedUser().getPhoneNumber();
		
	}
  


}