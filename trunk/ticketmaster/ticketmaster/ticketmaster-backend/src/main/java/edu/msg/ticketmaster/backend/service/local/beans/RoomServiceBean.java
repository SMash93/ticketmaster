package edu.msg.ticketmaster.backend.service.local.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.msg.ticketmaster.backend.model.Room;
import edu.msg.ticketmaster.backend.repository.RoomRepository;
import edu.msg.ticketmaster.backend.service.local.RoomService;

@Stateless
@LocalBean
public class RoomServiceBean implements RoomService {
	@EJB
	private RoomRepository rep;

	public RoomServiceBean() {

	}

	@Override
	public List<Room> findAllRooms() {
		List<Room> rooms = rep.findAll();
		return rooms;
	}

	@Override
	public void insertRoom(Room room) {
		rep.persist(room);
	}

	@Override
	public void deleteRoom(Room room) {
		// Room myRoom = findRoomByID(1L);
		rep.delete(room);
	}

	@Override
	public void updateRoom(Room room) {
		rep.update(room);
	}

	@Override
	public Room findRoomByID(Long roomId) {
		return rep.findByID(roomId);
	}

	@Override
	public Integer getNoRowsForRoomId(Integer roomId) {
		return rep.getNoRowsForRoomId(roomId);
	}

	@Override
	public Integer getNoColumnForRoomId(Integer roomId) {
		return rep.getNoColsForRoomId(roomId);
	}

}
