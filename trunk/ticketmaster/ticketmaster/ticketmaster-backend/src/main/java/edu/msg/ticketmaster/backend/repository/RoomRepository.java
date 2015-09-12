package edu.msg.ticketmaster.backend.repository;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Room;

@Local
public interface RoomRepository extends BaseRepository<Room, Long> {
	List<Room> getRoomsFromSchedules();
	Integer getNoRowsForRoomId(Integer roomId);

	Integer getNoColsForRoomId(Integer roomId);

}
