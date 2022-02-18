package logic;

import java.util.LinkedList;

import data.PublicationData;
import data.ServiceData;
import entities.Publication;
import entities.Service;

public class PublicationBarber {
	
	public static LinkedList<Publication> getAll(){
		PublicationData pd = new PublicationData();
		return pd.getAll();
	}
	
	public static Publication getById(int publicationId){
		PublicationData pd = new PublicationData();
		return pd.getById(publicationId);
	}
	
	public static LinkedList<Publication> getByBarberId(int barberId){
		PublicationData pd = new PublicationData();
		return pd.getByBarberId(barberId);
	}
	
	public static int add(Publication publication){
		PublicationData pd = new PublicationData();
		return pd.add(publication);
	}
	
	public static boolean update(Publication publication){
		PublicationData pd = new PublicationData();
		return pd.update(publication);
	}
	
	public static boolean arePublicationsLoaded(){
		PublicationData pd = new PublicationData();
		return !pd.getAll().isEmpty();
	}
	
	public static boolean delete(int id){
	    PublicationData pd = new PublicationData();
	    Publication publication = pd.getById(id);
	    return pd.delete(publication);
	  }
}
