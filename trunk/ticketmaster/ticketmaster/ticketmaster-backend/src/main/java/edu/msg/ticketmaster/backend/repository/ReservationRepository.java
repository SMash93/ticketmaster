package edu.msg.ticketmaster.backend.repository;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Reservation;

@Local
public interface ReservationRepository extends BaseRepository<Reservation, Long> {

}
