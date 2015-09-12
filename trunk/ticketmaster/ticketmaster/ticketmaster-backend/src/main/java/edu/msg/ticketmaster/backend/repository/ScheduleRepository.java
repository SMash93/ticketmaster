package edu.msg.ticketmaster.backend.repository;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Schedule;

@Local
public interface ScheduleRepository extends BaseRepository<Schedule, Long> {
	List<Schedule> getSchedulesFromReservations();
}
