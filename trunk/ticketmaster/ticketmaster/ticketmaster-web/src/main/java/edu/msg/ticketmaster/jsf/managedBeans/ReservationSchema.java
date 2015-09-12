package edu.msg.ticketmaster.jsf.managedBeans;

import java.util.Date;

public class ReservationSchema {

	private String userName;
	private String movieTitle;
	private Date broadcastDate;
	private Integer noOfSeats;
	private Integer seatRow;
	private Integer seatColumn;
	private String roomName;
	private String state;

	public ReservationSchema() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Date getBroadcastDate() {
		return broadcastDate;
	}

	public void setBroadcastDate(Date broadcastDate) {
		this.broadcastDate = broadcastDate;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public Integer getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(Integer seatRow) {
		this.seatRow = seatRow;
	}

	public Integer getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(Integer seatColumn) {
		this.seatColumn = seatColumn;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
