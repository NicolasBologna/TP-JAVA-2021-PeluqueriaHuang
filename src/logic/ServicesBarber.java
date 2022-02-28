package logic;

import java.time.LocalTime;
import java.util.LinkedList;

import data.PublicationData;
import data.ScheduleData;
import data.ServiceBarberData;
import data.ServiceData;
import entities.Service;
import entities.ServiceBarber;
import entities.User;

public class ServicesBarber {

	public static ServiceBarber getById(int serviceBarberId){
		ServiceBarberData sbd = new ServiceBarberData();
		return sbd.getById(serviceBarberId);
	}
	
	public static LinkedList<ServiceBarber> getByBarberId(int barberId){
		ServiceBarberData sbd = new ServiceBarberData();
		return sbd.getByBarberId(barberId);
	}
	
	public static LinkedList<Integer> getServicesByBarberId(int barberId){
		ServiceBarberData sbd = new ServiceBarberData();
		return sbd.getServicesByBarberId(barberId);
	}
	
	public static int add(ServiceBarber serviceBarber){
		ServiceBarberData sbd = new ServiceBarberData();
		return sbd.add(serviceBarber);
	}
	
	public static boolean addAll( String[] servicesBarber, int userId){
		ServiceBarberData sbd = new ServiceBarberData();
	    return sbd.addAll(servicesBarber, userId);
	  }
			
	public static boolean delete(ServiceBarber serviceBarber){
		ServiceBarberData sbd = new ServiceBarberData();
		return sbd.delete(serviceBarber);
	}
	
	public static boolean deleteAllByBarber(int barberId){
		ServiceBarberData sbd = new ServiceBarberData();
		return sbd.deleteAllByBarber(barberId);
	}
	
	public static boolean areServiceBarberLoaded(int barberId){
		ServiceBarberData sbd = new ServiceBarberData();
		return !sbd.getServicesByBarberId(barberId).isEmpty();
	}
	
	public static LinkedList<User> getBarbersByServices(String[] servicesId,int idLocal){
		ServiceBarberData sd = new ServiceBarberData();
		return sd.getBarberByServices(servicesId, idLocal);
		 
	}
	
	public static LocalTime getTotalDuration(String[] servicesId) {
		ServiceData sd = new ServiceData();
		return sd.getTotalDuration(servicesId);
	}
	
	public static LinkedList<Service> getServicesById(String[] servicesId){
		
		ServiceData sd = new ServiceData();
		return sd.getServicesById(servicesId);
	}
}
