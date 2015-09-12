package edu.msg.ticketmaster.backend.service.local;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Reservation;
import edu.msg.ticketmaster.backend.model.Seat;

@Local
public interface ReservationService {
	public List<Reservation> findAllReservations();

	public void deleteReservation(Reservation reservation);

	public Reservation findByIDReservation(Long reservationId);

	public void insertReservation(Reservation reservation);

	public void updateReservation(Reservation reservation);

	public Seat getSeatByReservationId(Long idRes);

}
