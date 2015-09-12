package edu.msg.ticketmaster.backend.repository.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.msg.ticketmaster.backend.model.Reservation;
import edu.msg.ticketmaster.backend.repository.ReservationRepository;

@Stateless
@LocalBean
public class ReservationRepositoryBean extends BaseRepositoryBean<Reservation, Long>implements ReservationRepository {

	public ReservationRepositoryBean() {
		super(Reservation.class);
	}

}
