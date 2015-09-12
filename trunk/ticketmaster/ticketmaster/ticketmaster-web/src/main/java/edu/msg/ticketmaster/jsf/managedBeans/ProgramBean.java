package edu.msg.ticketmaster.jsf.managedBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import edu.msg.ticketmaster.backend.model.Movie;
import edu.msg.ticketmaster.backend.model.Schedule;
import edu.msg.ticketmaster.backend.model.User;
import edu.msg.ticketmaster.backend.service.local.ScheduleService;

@ViewScoped
@ManagedBean(name = "programBean")
public class ProgramBean implements Serializable {

	private static final long serialVersionUID = 8624525531575744058L;

	@EJB
	private ScheduleService scheduleService;

	private List<Movie> movies = new ArrayList<Movie>();
	private List<Schedule> schedules;
	private Movie selectedMovie;
	private String title;
	private String genre;
	private String description;
	private float rating;
	private int duration;
	private Date date = new Date();

	@PostConstruct
	public void init() {
		schedules = scheduleService.findAllSchedule();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public void handleDateSelect(SelectEvent event) {
		Date date = (Date) event.getObject();
		setDate(date);
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String selectedDate = dateFormatter.format(this.date);
		System.out.println("Date changed, now is: " + selectedDate);

		for (Schedule s : schedules) {
			System.out.println("Schedule size: " + schedules.size());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			String dateOnly = dateFormat.format(s.getBroadcastDate());
			System.out.println("Movie date: " + dateOnly);
			// String timeOnly = timeFormat.format(s.getBroadcastDate());
			if (dateOnly.compareTo(selectedDate) == 0) {
				System.out.println("equals");
				movies.add(s.getMovie());
				System.out.println("Movies size: " + movies.size());
			} else
				System.out.println("Dates not equal");
		}
	}

	// working on that..
	public void makeReservation() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User loggedUser = (User) session.getAttribute("LoggedUser");
	}
}
