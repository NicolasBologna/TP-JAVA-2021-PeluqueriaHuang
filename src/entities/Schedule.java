package entities;

import java.time.LocalTime;

import utils.Days;

public class Schedule implements Comparable<Schedule> {
	protected int id;
	protected User barber;
	protected Local local;
	protected Days day_of_week;
	protected LocalTime start_time;
	protected LocalTime end_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getBarber() {
		return barber;
	}
	public void setBarber(User barber) {
		this.barber = barber;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public Days getDay_of_week() {
		return day_of_week;
	}
	public void setDay_of_week(Days day_of_week) {
		this.day_of_week = day_of_week;
	}
	public LocalTime getStart_time() {
		return start_time;
	}
	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}
	public LocalTime getEnd_time() {
		return end_time;
	}
	public void setEnd_time(LocalTime end_time) {
		this.end_time = end_time;
	}
	public String getDay_of_week_As_string() {
		return day_of_week.name();
	}
	
	public int compareTo(Schedule otherSchedule) {
        return this.day_of_week.ordinal() - otherSchedule.day_of_week.ordinal();
    }
}
