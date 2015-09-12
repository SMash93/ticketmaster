package edu.msg.ticketmaster.backend.service.local.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.model.UserType;
import edu.msg.ticketmaster.backend.repository.UserRepository;
import edu.msg.ticketmaster.backend.repositoryException.RepositoryException;
import edu.msg.ticketmaster.backend.service.local.UserService;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;
import edu.msg.ticketmaster.backend.utils.PasswordEncrypter;

@Stateless
@LocalBean
public class UserServiceBean implements UserService {

	private PasswordEncrypter passEnc;

	@EJB
	UserRepository repo;

	public UserServiceBean() {

	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {
			passEnc = new PasswordEncrypter();
			user.setPassword(PasswordEncrypter.generateHashedPassword(user.getPassword()));
			repo.update(user);
		} catch (RepositoryException e) {
			throw new ServiceException("Updating user failed!");
		}
	}

	@Override
	public void insertUser(User user) throws ServiceException {
		try {
			passEnc = new PasswordEncrypter();
			user.setPassword(PasswordEncrypter.generateHashedPassword(user.getPassword()));
			repo.persist(user);
		} catch (RepositoryException e) {
			throw new ServiceException("Inserting user failed!");
		}
	}

	@Override
	public void deleteUser(User user) throws ServiceException {
		try {
			repo.delete(user);
		} catch (RepositoryException e) {
			throw new ServiceException("Deleting user failed!");
		}

	}

	@Override
	public User findByIdUser(Long id) throws ServiceException {
		try {
			return repo.findByID(id);
		} catch (RepositoryException e) {
			throw new ServiceException("Return user by id failed!");
		}

	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		try {
			return repo.findAll();
		} catch (RepositoryException e) {
			throw new ServiceException("Retrieving users failed!");
		}
	}

	@Override
	public User validate(String username, String password) throws ServiceException {
		try {
			List<User> users = repo.findAll();
			String hashedPass = PasswordEncrypter.generateHashedPassword(password);
			for (User u : users) {
				if ((u.getUsername().equals(username)) && (u.getPassword().equals(hashedPass))) {
					return u;
				}
			}
			return null;
		} catch (RepositoryException e) {
			throw new ServiceException("Login failed!");
		}
	}

	@Override
	public boolean signUp(User user) throws ServiceException {

		try {

			user.setPassword(PasswordEncrypter.generateHashedPassword(user.getPassword()));
			user.setUserType(UserType.CLIENT);
			repo.persist(user);
			return true;
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to register");
		}

	}

	@Override
	public User findByUsername(User user) throws ServiceException {

		List<User> users;
		User returnableUser = new User();

		try {
			// return repo.findByID(id);
			users = repo.findAll();
			for (User u : users) {
				if (u.getUsername().equals(user.getUsername())) {
					returnableUser = u;
				}
			}

		} catch (RepositoryException e) {
			throw new ServiceException("Return user by id failed!");
		}

		return returnableUser;
	}

}
