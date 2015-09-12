package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.service.local.MovieService;
import edu.msg.ticketmaster.backend.serviceException.ServiceException;

@ManagedBean(name = "managManagedBean")

public class ManagManagedBean implements Serializable {

	private static final long serialVersionUID = 5527766272026177434L;

	@EJB
	private MovieService movieService;

	private List<Movie> movies;
	private List<Movie> movies1;

	public List<Movie> getMovies1() {
		return movies1;
	}

	public void setMovies1(List<Movie> movies1) {
		this.movies1 = movies1;
	}

	private String title;
	private String genre;
	private String description;
	private float rating;
	private int duration;
	private int isActive;
	private Movie selectedMovie;

	@PostConstruct
	public void init() {
		movies = movieService.findActiveMovies();

		// movies = movieService.findAllMovies();
		// for (Movie m : movies) {
		// if (m.getIsActive() == 1) {
		// System.out.println(m.getTitle());
		// }
		// }
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	public String addMovie() {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setGenre(genre);
		movie.setDescription(description);
		movie.setRating(rating);
		movie.setDuration(duration);
		movie.setIsActive(1);
		try {
			movieService.insertMovie(movie);
			return "management";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "management";
		}
	}

	public void emptyInput() {
		title = "";
		genre = "";
		description = "";
		rating = 0;
		duration = 0;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Movie Edited", ((Movie) event.getObject()).getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Movie) event.getObject()).getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Movie Selected", ((Movie) event.getObject()).getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Movie Unselected", ((Movie) event.getObject()).getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteMovie(Movie m) {
		System.out.println("before try");
		m.setIsActive(0);
		movieService.updateMovie(m);
		movies = movieService.findActiveMovies();
	}

}
