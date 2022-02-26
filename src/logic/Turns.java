package logic;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;

import data.PublicationData;
import data.TurnData;
import entities.Publication;
import entities.Turn;

public class Turns {
	
	public static LinkedList<Turn> getAll(){
		TurnData td = new TurnData();
		return td.getAll();
	}
	
	public static Turn getById(int turnId){
		TurnData td = new TurnData();
		return td.getById(turnId);
	}
	
	public static LinkedList<Turn> getByBarberAndLocal(int barberId, int localId){
		TurnData td = new TurnData();
		return td.getByBarberAndLocal(barberId, localId);
	}
	
	public static LinkedList<Turn> getByBarberId(int barberId){
		TurnData td = new TurnData();
		return td.getBookedTurnsByBarberId(barberId);
	}
	
	public static int add(Turn turn){
		TurnData td = new TurnData();
		return td.add(turn);
	}
	
	public static LinkedList<String> getHoursAvailable(int barber_id,String turnDate,int idLocal, LocalTime duration){
		
		TurnData td = new TurnData();
		return getHoursAvailable(barber_id,turnDate,idLocal,duration);
	}
	public static boolean delete(int id){
		TurnData td = new TurnData();
		Turn turn = td.getById(id);
	    return td.delete(turn);
	  }
}
