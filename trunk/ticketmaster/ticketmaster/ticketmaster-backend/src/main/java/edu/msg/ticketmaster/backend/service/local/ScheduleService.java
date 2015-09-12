package edu.msg.ticketmaster.backend.service.local;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Schedule;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@Local
public interface ScheduleService {

	public void deleteSchedule(Schedule schedule) throws ServiceException;

	public List<Schedule> findAllSchedule() throws ServiceException;

	public Schedule findByIDSchedule(Long id) throws ServiceException;

	public void insertSchedule(Schedule schedule) throws ServiceException;

	public void updateSchedule(Schedule schedule) throws ServiceException;
}
