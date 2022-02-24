package logic;

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
	
	public static int add(Turn turn){
		TurnData td = new TurnData();
		return td.add(turn);
	}
	
	public static boolean delete(int id){
		TurnData td = new TurnData();
		Turn turn = td.getById(id);
	    return td.delete(turn);
	  }
}
