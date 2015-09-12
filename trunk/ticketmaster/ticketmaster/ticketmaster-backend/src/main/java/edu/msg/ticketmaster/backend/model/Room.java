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
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
@Table(name="room")
public class Room extends BaseEntity implements Serializable {

	@Override
	public String toString() {
		return "Room [name=" + name + ", noColumn=" + noColumn + ", noRow=" + noRow + "]";
	}

	private static final long serialVersionUID = 7320951752148883594L;

	@Column(name="name")
	private String name;
	
	@Column(name="noColumn")
	private int noColumn;
	
	@Column(name="noRow")
	private int noRow;

	// bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy = "room")
	private List<Schedule> schedules;

	public Room() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoColumn() {
		return this.noColumn;
	}

	public void setNoColumn(int noColumn) {
		this.noColumn = noColumn;
	}

	public int getNoRow() {
		return this.noRow;
	}

	public void setNoRow(int noRow) {
		this.noRow = noRow;
	}
	
	@XmlTransient
	public List<Schedule> getSchedules() {
		return this.schedules;
	}
	
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Schedule addSchedule(Schedule schedule) {
		getSchedules().add(schedule);
		schedule.setRoom(this);

		return schedule;
	}

	public Schedule removeSchedule(Schedule schedule) {
		getSchedules().remove(schedule);
		schedule.setRoom(null);

		return schedule;
	}
}
