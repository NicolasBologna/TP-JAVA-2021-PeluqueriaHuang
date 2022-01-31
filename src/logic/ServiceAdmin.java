package logic;
import java.util.LinkedList;

import data.LocalData;
import data.ServiceData;
import entities.Service;
public class ServiceAdmin {
	
      public static LinkedList<Service> getAll(){
	    ServiceData sd = new ServiceData();
	    return sd.getAll();
	  }
	  
	  public static int add(Service service){
	    ServiceData sd = new ServiceData();
	    return sd.add(service);
	  }
	  
	  public static boolean update(Service service){
	    ServiceData sd = new ServiceData();
	    return sd.update(service);
	  }
	    
	  public static boolean delete(Service service){
	    ServiceData sd = new ServiceData();
	    return sd.delete(service);
	  }
	  public static boolean areServicesLoaded() {
			ServiceData sd = new ServiceData();
			return !sd.getAll().isEmpty();
		}

}
