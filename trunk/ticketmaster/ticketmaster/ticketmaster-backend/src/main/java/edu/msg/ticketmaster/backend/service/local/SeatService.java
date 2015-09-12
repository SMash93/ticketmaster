package edu.msg.ticketmaster.backend.service.local;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Seat;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@Local
public interface SeatService {

	public String printSomething();

	public void deleteSeat(Seat seat) throws ServiceException;

	public List<Seat> findAllSeats() throws ServiceException;

	public Seat findByIDSeat(Long id) throws ServiceException;

	public void insertSeat(Seat seat) throws ServiceException;

	public void updateSeat(Seat seat) throws ServiceException;

	public boolean verifySeat(Integer rowIdx, Integer colIdx);

}
