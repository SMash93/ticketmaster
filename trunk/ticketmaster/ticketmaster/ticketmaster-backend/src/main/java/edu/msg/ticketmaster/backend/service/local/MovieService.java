package edu.msg.ticketmaster.backend.service.local;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@Local
public interface MovieService extends BaseEntityService<Movie, Long> {

	public void insertMovie(Movie m) throws ServiceException;

	public void deleteMovie(Movie m) throws ServiceException;

	public void updateMovie(Movie m) throws ServiceException;

	public Movie findByIdMovie(Long id) throws ServiceException;

	public List<Movie> findAllMovies() throws ServiceException;

	public void deactivateMovie(Movie m) throws ServiceException;

	public List<Movie> findActiveMovies() throws ServiceException;

	public boolean checkDateForMovie(Movie movie) throws ServiceException;

}