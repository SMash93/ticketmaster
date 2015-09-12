package edu.msg.ticketmaster.jsf.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.service.local.MovieService;

@ManagedBean
public class MovieManagedBean {
	@EJB
	private MovieService movieService;
	private List<Movie> movieList;

	@PostConstruct
	public void init() {
		movieList = movieService.findActiveMovies();
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}
}
