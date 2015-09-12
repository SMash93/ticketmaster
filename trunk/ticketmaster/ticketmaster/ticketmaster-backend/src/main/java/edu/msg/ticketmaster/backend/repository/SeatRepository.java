package edu.msg.ticketmaster.backend.repository;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Reservation;
import edu.msg.ticketmaster.backend.model.Seat;

@Local
public interface SeatRepository extends BaseRepository<Seat, Long> {

	List<Reservation> getReservationsFromSeats();

	boolean verifySeat(Integer rowIdx, Integer colIdx);

}
