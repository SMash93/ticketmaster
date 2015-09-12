package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import edu.msg.ticketmaster.backend.model.Room;
import edu.msg.ticketmaster.backend.service.local.RoomService;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@ManagedBean(name = "roomManagedBean")
public class RoomManagedBean implements Serializable {

	private static final long serialVersionUID = -5208386015282696636L;

	@EJB
	private RoomService roomService;

	private List<Room> rooms;
	private Room roomSelected;

	@PostConstruct
	private void init() {

		rooms = roomService.findAllRooms();
	}

	private String roomName;
	private int noRow;
	private int noColumn;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getNoRow() {
		return noRow;
	}

	public void setNoRow(int noRow) {
		this.noRow = noRow;
	}

	public int getNoColumn() {
		return noColumn;
	}

	public void setNoColumn(int noColumn) {
		this.noColumn = noColumn;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room getRoomSelected() {
		return roomSelected;
	}

	public void setRoomSelected(Room roomSelected) {
		this.roomSelected = roomSelected;
	}

	public String addRoom() {

		Room room = new Room();
		room.setName(roomName);
		room.setNoRow(noRow);
		room.setNoColumn(noColumn);

		try {
			roomService.insertRoom(room);
			return "rooms_management";
		} catch (ServiceException e) {
			return "rooms_management";
		}

	}
	
	public String deleteRoom(Room room){
		
		roomService.deleteRoom(room);
		rooms = roomService.findAllRooms();
		return "rooms_management";
	}

}
