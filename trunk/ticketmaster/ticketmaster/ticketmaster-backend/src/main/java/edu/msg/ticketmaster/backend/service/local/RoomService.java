package edu.msg.ticketmaster.backend.service.local;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Room;

@Local
public interface RoomService {
	public List<Room> findAllRooms();

	public void insertRoom(Room room);

	public void deleteRoom(Room room);

	public void updateRoom(Room room);

	public Room findRoomByID(Long roomId);

	public Integer getNoRowsForRoomId(Integer roomId);

	public Integer getNoColumnForRoomId(Integer roomId);

}
