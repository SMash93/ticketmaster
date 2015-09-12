package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.model.Reservation;
import edu.msg.ticketmaster.backend.model.Schedule;
import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.repository.MovieRepository;
import edu.msg.ticketmaster.backend.repository.ReservationRepository;
import edu.msg.ticketmaster.backend.repository.ScheduleRepository;
import edu.msg.ticketmaster.backend.repository.SeatRepository;
import edu.msg.ticketmaster.backend.repository.UserRepository;
import edu.msg.ticketmaster.backend.service.local.ReservationService;

@ManagedBean(name = "reservationManagedBean")
public class ReservationManagedBean implements Serializable {

	private static final long serialVersionUID = -5308852789280222363L;
	private List<Reservation> reservations;
	private Reservation reservation;
	private List<String> userNames;
	private List<String> movieTitles;
	private List<String> rooms;
	private List<Date> broadcastDate;
	private List<String> states;

	@EJB
	ReservationRepository resRepo;
	@EJB
	UserRepository userRepo;
	@EJB
	MovieRepository movieRepo;
	@EJB
	ScheduleRepository scheduleRepo;
	@EJB
	SeatRepository seatRepo;
	@EJB
	ReservationService resService;

	@PostConstruct
	public void init() {
		reservations = resRepo.findAll();
	}

	public ReservationManagedBean() {

	}

	public List<String> getUserName() {
		List<User> users = userRepo.getUsersFromReservations();
		for (User u : users) {
			userNames.add(u.getUsername());
		}
		return userNames;
	}

	public void setUserName(List<String> userName) {
		this.userNames = userName;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<String> getMovieTitle() {
		List<Movie> movies = movieRepo.getMoviesFromReservations();
		for (Movie m : movies) {
			movieTitles.add(m.getTitle());
		}
		return movieTitles;
	}

	public void setMovieTitle(List<String> movieTitles) {
		this.movieTitles = movieTitles;
	}

	public List<String> getRoom() {
		List<Schedule> schedules = scheduleRepo.getSchedulesFromReservations();
		for (Schedule s : schedules) {
			rooms.add(s.getRoom().getName());
		}

		return rooms;
	}

	public void setRoom(List<String> rooms) {
		this.rooms = rooms;
	}

	public List<Date> getBroadcastDate() {
		List<Schedule> schedules = scheduleRepo.getSchedulesFromReservations();
		for (Schedule s : schedules) {
			broadcastDate.add(s.getBroadcastDate());
		}
		return broadcastDate;
	}

	public void setBroadcastDate(List<Date> broadcastDate) {
		this.broadcastDate = broadcastDate;
	}

	public List<String> getState() {
		for (Reservation r : reservations) {
			states.add(r.getState().toString());
		}
		return states;
	}

	public void setState(List<String> states) {
		this.states = states;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
