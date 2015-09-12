package edu.msg.ticketmaster.backend.service.local.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.msg.ticketmaster.backend.model.Seat;
import edu.msg.ticketmaster.backend.repository.SeatRepository;
import edu.msg.ticketmaster.backend.repositoryException.RepositoryException;
import edu.msg.ticketmaster.backend.service.local.SeatService;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@Stateless
@LocalBean
public class SeatServiceBean implements SeatService {

	public SeatServiceBean() {

	}

	@EJB
	SeatRepository rep;

	@Override
	public String printSomething() {
		List<Seat> seats = rep.findAll();
		return seats.toString();
	}

	@Override
	public void deleteSeat(Seat seat) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Seat> findAllSeats() {

		try {
			return rep.findAll();
		} catch (RepositoryException e) {
			throw new RepositoryException("Failed to get all seats!");
		}
	}

	@Override
	public Seat findByIDSeat(Long id) {

		try {
			return rep.findByID(id);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to get the specified seat! ");
		}
	}

	@Override
	public void insertSeat(Seat seat) {

		try {
			rep.persist(seat);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to insert the seat! ");
		}
	}

	@Override
	public void updateSeat(Seat seat) {

		try {
			rep.update(seat);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to update the seat! ");
		}
	}

	@Override
	public boolean verifySeat(Integer rowIdx, Integer colIdx) {
		return rep.verifySeat(rowIdx, colIdx);
	}

}
