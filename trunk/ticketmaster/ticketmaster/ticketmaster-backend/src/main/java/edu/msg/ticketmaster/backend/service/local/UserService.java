package edu.msg.ticketmaster.backend.service.local;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@Local
public interface UserService {
	public void updateUser(User user) throws ServiceException;

	public void insertUser(User user) throws ServiceException;

	public void deleteUser(User user) throws ServiceException;

	public User findByIdUser(Long id) throws ServiceException;

	public List<User> findAllUsers() throws ServiceException;

	public User validate(String username, String password) throws ServiceException;

	public boolean signUp(User user) throws ServiceException;

	public User findByUsername(User user) throws ServiceException;

}
