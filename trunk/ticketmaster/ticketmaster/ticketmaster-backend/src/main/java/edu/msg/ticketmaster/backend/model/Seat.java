package edu.msg.ticketmaster.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQuery(name = "Seat.findAll", query = "SELECT s FROM Seat s")
@Table(name="seat")
public class Seat extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7294270790729470742L;

	@Column(name="seatcolumn")
	private int seatColumn;
	
	@Column(name="seatrow")
	private int seatRow;

	// bi-directional many-to-one association to Reservation
	@ManyToOne
	@JoinColumn(name = "idReservation")
	private Reservation reservation;

	public Seat() {
	}

	public int getSeatColumn() {
		return this.seatColumn;
	}

	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}

	public int getSeatRow() {
		return this.seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	
	@XmlTransient
	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	@Override
	public String toString() {
		return "Seat [seatcolumn=" + seatColumn + ", seatrow=" + seatRow + "]";
	}

}
