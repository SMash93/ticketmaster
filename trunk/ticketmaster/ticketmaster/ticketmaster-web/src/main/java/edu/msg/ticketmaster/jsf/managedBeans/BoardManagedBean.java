package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.ticketmaster.backend.model.Reservation;
import edu.msg.ticketmaster.backend.model.Seat;
import edu.msg.ticketmaster.backend.service.local.ReservationService;
import edu.msg.ticketmaster.backend.service.local.RoomService;
import edu.msg.ticketmaster.backend.service.local.SeatService;

@ManagedBean
@ViewScoped
public class BoardManagedBean implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(ManagManagedBean.class);
	private static final long serialVersionUID = 5707908368362814359L;
	// @EJB
	// SeatService seatService;

	@EJB
	private RoomService roomService;
	@EJB
	private SeatService seatService;
	@EJB
	private ReservationService reservationService;

	private boolean[][] reservedSeats;
	private boolean[][] disabledSeats;
	private Integer nrCols;
	private Integer nrRows;
	private int roomId;

	@PostConstruct
	public void init() {
		nrRows = roomService.getNoRowsForRoomId(Integer.valueOf(1));
		nrCols = roomService.getNoColumnForRoomId(Integer.valueOf(1));
		reservedSeats = new boolean[nrRows][nrCols];
		disabledSeats = new boolean[nrRows][nrCols];
	}

	public void toogleSeat(Integer rowIdx, Integer colIdx) {
		if (reservedSeats[rowIdx][colIdx] == false)
			reservedSeats[rowIdx][colIdx] = true;
		else
			reservedSeats[rowIdx][colIdx] = false;
	}

	public List<Integer> getSeatList() {
		List<Integer> seatList = new ArrayList<Integer>(25);

		return seatList;

	}

	public Integer[] getNrCols() {
		return new Integer[nrCols];
	}

	public void setNrCols(Integer nrCols) {
		this.nrCols = nrCols;
	}

	public Integer[] getNrRows() {
		return new Integer[nrRows];
	}

	public void setNrRows(Integer nrRows) {
		this.nrRows = nrRows;
	}

	public String getStyle(Integer rowIdx, Integer colIdx) {
		logger.error("rowIdx" + rowIdx + "  " + colIdx);
		if (reservedSeats[rowIdx][colIdx] == true) {

			return "reservedSeat";
		}
		if (seatService.verifySeat(rowIdx, colIdx) == true) {

			return "availableSeat";
		} else {
			disabledSeats[rowIdx][colIdx] = true;
			return "unavailableSeat";

		}
	}

	public void reserveSeats(Integer reservationId) {
		Seat seat;
		Reservation reservation;
		for (int row = 0; row < reservedSeats.length; row++) {
			for (int column = 0; column < reservedSeats[row].length; column++) {
				if (reservedSeats[row][column] == true) {
					seat = new Seat();
					seat.ensureUUID();
					seat.setSeatRow(row);
					seat.setSeatColumn(column);
					reservation = reservationService.findByIDReservation(Long.valueOf(reservationId));
					seat.setReservation(reservation);
					seatService.insertSeat(seat);
					reservedSeats[row][column] = false;
				}
			}
		}
	}

	public boolean tryToDisable(Integer rowIdx, Integer colIdx) {
		return disabledSeats[rowIdx][colIdx];
	}

}
