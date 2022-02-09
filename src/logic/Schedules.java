package logic;

import java.util.LinkedList;

import data.LocalData;
import data.ScheduleData;
import entities.Local;
import entities.Schedule;

public class Schedules {
	public static LinkedList<Schedule> getAllByBarber(int barberId){
		ScheduleData scheduleData = new ScheduleData();
		return scheduleData.getAllByBarber(barberId);
	}
	
	public static boolean areSchedulesLoaded(int barberId) {
		ScheduleData scheduleData = new ScheduleData();
		return !scheduleData.getAllByBarber(barberId).isEmpty();
	}
	
	public static boolean isTimeAvailable() {
		return true;
	}
	
	public static int add(Schedule newSchedule){
		ScheduleData sData = new ScheduleData();
		return sData.add(newSchedule);
	}
}
