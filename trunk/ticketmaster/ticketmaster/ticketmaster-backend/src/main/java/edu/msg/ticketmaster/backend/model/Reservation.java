package edu.msg.ticketmaster.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r")
@Table(name = "reservation")
public class Reservation extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5247200911818470335L;

	@Column(name = "email")
	private String email;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;

	// bi-directional many-to-one association to Schedule
	@ManyToOne
	@JoinColumn(name = "idSchedule")
	private Schedule schedule;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	// bi-directional many-to-one association to Seat

	@OneToMany(mappedBy = "reservation")
	private List<Seat> seats;

	public Reservation() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// @XmlTransient
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@XmlTransient
	public Schedule getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@XmlTransient
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@XmlTransient
	public List<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Seat addSeat(Seat seat) {
		getSeats().add(seat);
		seat.setReservation(this);

		return seat;
	}

	public Seat removeSeat(Seat seat) {
		getSeats().remove(seat);
		seat.setReservation(null);

		return seat;
	}

	@Override
	public String toString() {
		return "id Reservation =" + getId();
	}

}
