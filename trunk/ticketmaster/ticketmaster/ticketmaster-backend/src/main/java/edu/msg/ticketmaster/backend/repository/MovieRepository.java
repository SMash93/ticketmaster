package edu.msg.ticketmaster.backend.repository;

import java.util.List;

import javax.ejb.Local;

import edu.msg.ticketmaster.backend.model.Movie;

@Local
public interface MovieRepository extends BaseRepository<Movie, Long> {

	List<Movie> getMoviesFromSchedules();

	List<Movie> getMoviesFromReservations();

	public void deactivateMovie(Movie movie);

	public List<Movie> findActiveMovies();

	boolean checkDateForMovie(Movie movie);

}
