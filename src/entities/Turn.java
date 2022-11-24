package entities;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Turn {

	private int turnId;
	private Schedule schedule;
	private User client;
	private LocalDate date;
	private LocalTime hour;
	private Time duration;
	private LinkedList<Service> services;
	private boolean not_cancelled;
	public int getTurnId() {
		return turnId;
	}
	
	public void setTurnId(int turnId) {
		this.turnId = turnId;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getHour() {
		return hour;
	}
	public void setHour(LocalTime hour) {
		this.hour = hour;
	}
	public LinkedList<Service> getServices() {
		return services;
	}
	public void setServices(LinkedList<Service> services) {
		this.services = services;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}



	public boolean getNot_cancelled() {
		return not_cancelled;
	}

	public void setNot_cancelled(boolean not_cancelled) {
		this.not_cancelled = not_cancelled;
	}
	
	
	
}
