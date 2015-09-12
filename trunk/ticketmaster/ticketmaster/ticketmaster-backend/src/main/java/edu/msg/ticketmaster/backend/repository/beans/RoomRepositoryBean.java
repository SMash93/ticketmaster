package edu.msg.ticketmaster.backend.repository.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.msg.ticketmaster.backend.model.Room;
import edu.msg.ticketmaster.backend.repository.RoomRepository;

@Stateless
@LocalBean
public class RoomRepositoryBean extends BaseRepositoryBean<Room, Long>implements RoomRepository {

	private EntityManager entityManager;

	public RoomRepositoryBean() {
		super(Room.class);
	}

	@Override
	public List<Room> getRoomsFromSchedules() {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT r FROM Room r inner join Schedule s on s.id = r.id");
		return query.getResultList();
	}

	@Override
	public Integer getNoRowsForRoomId(Integer roomId) {
		long lroomId = Long.valueOf(roomId);
		Room room = findByID(lroomId);
		return room.getNoRow();
	}

	@Override
	public Integer getNoColsForRoomId(Integer roomId) {
		long lroomId = Long.valueOf(roomId);
		Room room = findByID(lroomId);
		return room.getNoColumn();
	}

}
