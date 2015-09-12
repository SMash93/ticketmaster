package edu.msg.ticketmaster.backend.service.local.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.msg.ticketmaster.backend.model.Schedule;
import edu.msg.ticketmaster.backend.repository.ScheduleRepository;
import edu.msg.ticketmaster.backend.repositoryException.RepositoryException;
import edu.msg.ticketmaster.backend.service.local.ScheduleService;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@Stateless
@LocalBean
public class ScheduleServiceBean implements ScheduleService {

	public ScheduleServiceBean() {

	}

	@EJB
	private ScheduleRepository repo;

	@Override
	public void deleteSchedule(Schedule schedule) throws ServiceException {
		try {
			repo.delete(schedule);
		} catch (RepositoryException ex) {
			throw new ServiceException("Deleting schedule failed!", ex);
		}
	}

	@Override
	public List<Schedule> findAllSchedule() throws ServiceException {
		try {
			return repo.findAll();
		} catch (RepositoryException ex) {
			throw new ServiceException("Getting all schedules failed!", ex);
		}
	}

	@Override
	public Schedule findByIDSchedule(Long id) throws ServiceException {
		try {
			return repo.findByID(id);
		} catch (RepositoryException ex) {
			throw new ServiceException("Getting schedule by id failed!", ex);
		}
	}

	@Override
	public void insertSchedule(Schedule schedule) throws ServiceException {
		try {
			repo.persist(schedule);
		} catch (RepositoryException ex) {
			throw new ServiceException("Inserting schedule failed!", ex);
		}

	}

	@Override
	public void updateSchedule(Schedule schedule) throws ServiceException {
		try {
			repo.update(schedule);
		} catch (RepositoryException ex) {
			throw new ServiceException("Inserting schedule failed!", ex);
		}

	}

}
