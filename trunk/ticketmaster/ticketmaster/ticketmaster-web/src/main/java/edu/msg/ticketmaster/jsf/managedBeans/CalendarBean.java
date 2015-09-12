package edu.msg.ticketmaster.jsf.managedBeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name = "calendarBean")
public class CalendarBean {

	private Date date = new Date();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
