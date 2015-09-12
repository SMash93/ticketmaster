package edu.msg.ticketmaster.backend.repository.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.msg.ticketmaster.backend.model.Schedule;
import edu.msg.ticketmaster.backend.repository.ScheduleRepository;

@Stateless
@LocalBean
public class ScheduleRepositoryBean extends BaseRepositoryBean<Schedule, Long>implements ScheduleRepository {

	private EntityManager entityManager;

	public ScheduleRepositoryBean() {
		super(Schedule.class);
	}

	@Override
	public List<Schedule> getSchedulesFromReservations() {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT s FROM Schedule s inner join Reservation r on r.id = s.id");
		return query.getResultList();
	}
}
