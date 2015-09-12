package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.service.local.MovieService;

@RequestScoped
@ManagedBean (name = "programBean")
public class ProgramBean implements Serializable{

	private static final long serialVersionUID = 8624525531575744058L;

	@EJB
    private MovieService movieService;
	
	private List<Movie> movies;
	private Movie selectedMovie;
	private String title;
	private String genre;
	private String description;
	private float rating;
	private int duration;
	
	@PostConstruct
    public void init() {
        movies = movieService.findAllMovies();
    }
	
	public Movie getSelectedMovie() {
        return selectedMovie;
    }
	
	public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }
	
	public List<Movie> getMovies() {
        return movies;
    }
	
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
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
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Movie Selected", ((Movie) event.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
	public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Movie Unselected", ((Movie) event.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void makeReservation() {
		
	}
}
