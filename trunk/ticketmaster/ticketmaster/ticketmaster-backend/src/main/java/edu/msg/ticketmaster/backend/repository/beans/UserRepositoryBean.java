package edu.msg.ticketmaster.backend.repository.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.repository.UserRepository;

@Stateless
@LocalBean
public class UserRepositoryBean extends BaseRepositoryBean<User, Long>implements UserRepository {
	private EntityManager entityManager;

	public UserRepositoryBean() {
		super(User.class);
	}

	@Override
	public List<User> getUsersFromReservations() {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT u FROM User u inner join Reservation r on r.id = u.id");
		return query.getResultList();
	}

}
