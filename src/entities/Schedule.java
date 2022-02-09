package entities;

import java.time.LocalTime;

import utils.Days;

public class Schedule {
	private int id;
	private int barber_id;
	private int local_id;
	private Days day_of_week;
	private LocalTime start_time;
	private LocalTime end_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBarber_id() {
		return barber_id;
	}
	public void setBarber_id(int barber_id) {
		this.barber_id = barber_id;
	}
	public int getLocal_id() {
		return local_id;
	}
	public void setLocal_id(int local_id) {
		this.local_id = local_id;
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
	
}
