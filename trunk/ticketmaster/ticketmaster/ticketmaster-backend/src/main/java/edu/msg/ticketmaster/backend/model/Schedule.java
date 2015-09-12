package edu.msg.ticketmaster.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")
@Table(name = "schedule")
public class Schedule extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2612090359805473469L;

	@Column(name = "broadcastDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date broadcastDate;

	@Column(name = "limitDate")
	@Temporal(TemporalType.DATE)
	private Date limitDate;

	@Column(name = "price")
	private float price;

	// bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy = "schedule")
	private List<Reservation> reservations;

	// bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name = "idMovie")
	private Movie movie;

	// bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name = "idRoom")
	private Room room;

	public Schedule() {
	}

	public Date getBroadcastDate() {
		return this.broadcastDate;
	}

	public void setBroadcastDate(Date broadcastDate) {
		this.broadcastDate = broadcastDate;
	}

	public Date getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public float getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "Schedule [broadcastDate=" + broadcastDate + ", limitDate=" + limitDate + ", price=" + price
				+ ", reservations=" + reservations.size() + ", movie=" + movie + ", room=" + room + "]";
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
