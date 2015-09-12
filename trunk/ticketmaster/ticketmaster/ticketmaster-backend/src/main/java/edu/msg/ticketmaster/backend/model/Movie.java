package edu.msg.ticketmaster.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
@Table(name="movie")
public class Movie extends BaseEntity implements Serializable {

	@Override
	public String toString() {
		return "Movie [description=" + description + ", duration=" + duration + ", genre=" + genre + ", isActive="
				+ isActive + ", rating=" + rating + ", title=" + title + "]";
	}

	private static final long serialVersionUID = -2195967036562611508L;

	@Column(name="description")
	private String description;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="isActive")
	private int isActive;
	
	@Column(name="rating")
	private float rating;
	
	@Column(name="title")
	private String title;

	// bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy = "movie")
	private List<Schedule> schedules;

	public Movie() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getIsActive() {
		return this.isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	public Schedule addSchedule(Schedule schedule) {
		getSchedules().add(schedule);
		schedule.setMovie(this);

		return schedule;
	}

	public Schedule removeSchedule(Schedule schedule) {
		getSchedules().remove(schedule);
		schedule.setMovie(null);

		return schedule;
	}	
}
