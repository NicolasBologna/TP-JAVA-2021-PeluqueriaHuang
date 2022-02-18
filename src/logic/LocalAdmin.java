package logic;

import java.util.LinkedList;

import data.LocalData;
import entities.Local;
import entities.User;

public class LocalAdmin {
	
	public static LinkedList<Local> getAll(){
		LocalData ld = new LocalData();
		return ld.getAll();
	}
	
	public static Local getById(int localId){
		LocalData ld = new LocalData();
		return ld.getById(localId);
	}
	
	public static int add(Local local){
		LocalData ld = new LocalData();
		return ld.add(local);
	}
	
	public static boolean update(Local local){
		LocalData ld = new LocalData();
		return ld.update(local);
	}
		
	public static boolean delete(Local local){
		LocalData ld = new LocalData();
		return ld.delete(local);
	}
	
	public static boolean checkAddressAvailability(String address) {
		LocalData ld = new LocalData();
		Local local = ld.getByAddress(address);
		return (local != null); 
	}
	
	public static boolean areLocalsLoaded() {
		LocalData ld = new LocalData();
		return !ld.getAll().isEmpty();
	}
	
	
	
	public static boolean switchLocalStatus(int id){
		LocalData ld = new LocalData();
		Local local =  ld.getById(id);
		return ld.switchLocalStatus((byte)(local.getIsEnable()?0:1), local.getLocalId());
	}
	
	public static LinkedList<User> getBarbersByLocal(int id){
		LocalData ld = new LocalData();
		return ld.getBarbers(id);
	
}}
