package entities;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Turn {

	private int turnId;
	private Schedule schedule;
	private User client;
	private LocalDateTime init_date;
	private LocalDateTime finish_date;
	private LinkedList<Service> services;
	
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
	public LocalDateTime getInit_date() {
		return init_date;
	}
	public void setInit_date(LocalDateTime init_date) {
		this.init_date = init_date;
	}
	public LocalDateTime getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(LocalDateTime finish_date) {
		this.finish_date = finish_date;
	}
	public LinkedList<Service> getServices() {
		return services;
	}
	public void setServices(LinkedList<Service> services) {
		this.services = services;
	}
}
