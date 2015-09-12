package edu.msg.ticketmaster.backend.service.local.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.msg.ticketmaster.backend.model.Reservation;
import edu.msg.ticketmaster.backend.model.Seat;
import edu.msg.ticketmaster.backend.repository.MovieRepository;
import edu.msg.ticketmaster.backend.repository.ReservationRepository;
import edu.msg.ticketmaster.backend.repository.ScheduleRepository;
import edu.msg.ticketmaster.backend.repository.SeatRepository;
import edu.msg.ticketmaster.backend.repository.UserRepository;
import edu.msg.ticketmaster.backend.service.local.ReservationService;

@Stateless
@LocalBean
public class ReservationServiceBean implements ReservationService {

	@EJB
	private ReservationRepository reservationRepo;

	@EJB
	private UserRepository userRepo;

	@EJB
	private MovieRepository movieRepo;

	@EJB
	private ScheduleRepository scheduleRepo;

	@EJB
	private SeatRepository seatRepo;

	public ReservationServiceBean() {

	}

	@Override
	public List<Reservation> findAllReservations() {
		return reservationRepo.findAll();
	}

	public String printSomethin() {
		return reservationRepo.findAll().toString();
	}

	@Override
	public void deleteReservation(Reservation reservation) {
		reservationRepo.delete(reservation);

	}

	@Override
	public Reservation findByIDReservation(Long reservationId) {
		return reservationRepo.findByID(reservationId);
	}

	@Override
	public void insertReservation(Reservation reservation) {

		reservationRepo.persist(reservation);

	}

	@Override
	public void updateReservation(Reservation reservation) {

		reservationRepo.update(reservation);

	}

	@Override
	public Seat getSeatByReservationId(Long idRes) {
		List<Seat> seats = seatRepo.findAll();
		Seat seat = new Seat();

		for (Seat s : seats) {
			if (s.getReservation().getId().equals(idRes)) {
				seat = s;
			}
		}

		System.out.println("seat row: " + seat.getSeatRow());

		return seat;
	}

}
