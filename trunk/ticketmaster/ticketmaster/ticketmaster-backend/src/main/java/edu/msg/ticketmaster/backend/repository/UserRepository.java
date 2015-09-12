package edu.msg.ticketmaster.backend.repository;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.User;

@Local
public interface UserRepository extends BaseRepository<User, Long> {

	List<User> getUsersFromReservations();
}
