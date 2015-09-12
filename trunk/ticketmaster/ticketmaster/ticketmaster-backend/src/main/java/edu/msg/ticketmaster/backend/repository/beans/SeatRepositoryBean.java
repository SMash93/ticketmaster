package edu.msg.ticketmaster.backend.repository.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.msg.ticketmaster.backend.model.Reservation;
import edu.msg.ticketmaster.backend.model.Seat;
import edu.msg.ticketmaster.backend.repository.SeatRepository;

@Stateless
@LocalBean
public class SeatRepositoryBean extends BaseRepositoryBean<Seat, Long>implements SeatRepository {

	private EntityManager entityManager;

	public SeatRepositoryBean() {
		super(Seat.class);
	}

	@Override
	public List<Reservation> getReservationsFromSeats() {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT r FROM Reservation r inner join Seat s on s.id = r.id");
		return query.getResultList();
	}

	@Override
	public boolean verifySeat(Integer rowIdx, Integer colIdx) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager
				.createQuery("SELECT x FROM Seat x WHERE x.seatRow = :noRow and x.seatColumn = :noColumn");
		q.setParameter("noRow", rowIdx);
		q.setParameter("noColumn", colIdx);
		return q.getResultList().size() == 0;
	}

}
