package edu.msg.ticketmaster.backend.service.local.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.repository.MovieRepository;
import edu.msg.ticketmaster.backend.repositoryException.RepositoryException;
import edu.msg.ticketmaster.backend.service.local.MovieService;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@Stateless
@LocalBean
public class MovieServiceBean implements MovieService {

	@EJB
	private MovieRepository rep;

	@Override
	public void insertMovie(Movie movie) throws ServiceException {

		try {

			rep.persist(movie);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to insert the movie! ");
		}

	}

	@Override
	public void deleteMovie(Movie movie) throws ServiceException {

		try {
			rep.delete(movie);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to delete the movie! ");
		}

	}

	@Override
	public void updateMovie(Movie movie) throws ServiceException {

		try {
			rep.update(movie);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to update the movie! ");
		}

	}

	@Override
	public Movie findByIdMovie(Long id) throws ServiceException {

		try {
			return rep.findByID(id);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to get the specified movie! ");
		}

	}

	@Override
	public List<Movie> findAllMovies() throws ServiceException {

		try {
			return rep.findAll();
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to get all movies!");
		}

	}

	@Override
	public void deactivateMovie(Movie m) throws ServiceException {

		try {

			m.setIsActive(0);
			System.out.println("inside deactivate");
			rep.deactivateMovie(m);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to deactivate the movie!");
		}

	}

	@Override
	public List<Movie> findActiveMovies() throws ServiceException {
		try {
			return rep.findActiveMovies();
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to find active movies!");
		}
	}

	@Override
	public boolean checkDateForMovie(Movie movie) throws ServiceException {
		try {
			return rep.checkDateForMovie(movie);
		} catch (RepositoryException e) {
			throw new ServiceException("Failed to find active movies!");
		}
	}

}
