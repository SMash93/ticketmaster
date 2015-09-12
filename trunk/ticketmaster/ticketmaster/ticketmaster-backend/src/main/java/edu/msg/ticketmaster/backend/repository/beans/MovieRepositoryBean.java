package edu.msg.ticketmaster.backend.repository.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.repository.MovieRepository;

@Stateless
@LocalBean
public class MovieRepositoryBean extends BaseRepositoryBean<Movie, Long>implements MovieRepository {

	private EntityManager entityManager;

	public MovieRepositoryBean() {
		super(Movie.class);
	}

	@Override
	public List<Movie> getMoviesFromSchedules() {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT m FROM Movie m inner join Schedule s on s.id = m.id");
		return query.getResultList();

	}

	@Override
	public List<Movie> getMoviesFromReservations() {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery(
				"SELECT m FROM Movie m inner join Schedule s on s.id = m.id inner join Reservation r on r.id = s.id inner join User u on r.id = u.id");
		return query.getResultList();

	}

	@Override
	public List<Movie> findActiveMovies() {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.isActive = 1");
		return query.getResultList();
	}

	@Override
	public boolean checkDateForMovie(Movie movie) {
		entityManager = getEntityManager();
		Query query = entityManager.createQuery(
				"select max(s.broadcastDate) from Schedule s inner join Movie m on m.id = s.movie.id where m.title = :title");
		query.setParameter("title", movie.getTitle());
		Date date = (Date) query.getSingleResult();
		Date currentDate = new Date();
		return (date.after(currentDate));
	}

}
