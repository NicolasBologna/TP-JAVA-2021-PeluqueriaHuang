package logic;

import java.util.LinkedList;

import data.ScheduleData;
import entities.Schedule;
import utils.Days;

public class Schedules {
	public static LinkedList<Schedule> getAllByBarber(int barberId){
		ScheduleData scheduleData = new ScheduleData();
		return scheduleData.getAllByBarber(barberId);
	}
	
	public static Schedule getByLocalBarber(int localId,int barberId) {
		ScheduleData sd = new ScheduleData();
		return sd.getByBarberLocal(localId,barberId);
	}
	
	
	public static boolean areSchedulesLoaded(int barberId) {
		ScheduleData scheduleData = new ScheduleData();
		return !scheduleData.getAllByBarber(barberId).isEmpty();
	}
	
	public static boolean isTimeAvailable() {
		return true;
	}
	
	public static boolean isDayOccupied(Schedule newSchedule) {
		ScheduleData scheduleData = new ScheduleData();
		LinkedList<Days> days= scheduleData.getDays(newSchedule);	
		
		if (days.size() == 0){
			return false;
		}
		else {
			return days.contains(newSchedule.getDay_of_week());
		}
		
	
	}
	public static int add(Schedule newSchedule){
		ScheduleData sData = new ScheduleData();
		return sData.add(newSchedule);
	}
	
	public static Schedule getById(int id) {
		ScheduleData sd = new ScheduleData();
		return sd.getById(id);
	}
	
	
	public static boolean update(Schedule editedSchedule){
		ScheduleData sData = new ScheduleData();
		return sData.update(editedSchedule);
	}
}
