package dtos;

import java.util.LinkedList;

import entities.Schedule;
import entities.User;

public class BarberWithWorkingDaysDto {
	private User barber;
	private LinkedList<Schedule> schedules;
	
	public User getBarber() {
		return barber;
	}
	public void setBarber(User barber) {
		this.barber = barber;
	}
	public LinkedList<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(LinkedList<Schedule> schedules) {
		this.schedules = schedules;
	}

}
