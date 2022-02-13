package logic;

import java.util.LinkedList;

import data.PublicationData;
import entities.Publication;

public class PublicationBarber {
	
	public static LinkedList<Publication> getAll(){
		PublicationData pd = new PublicationData();
		return pd.getAll();
	}
	
	public static Publication getById(int publicationId){
		PublicationData pd = new PublicationData();
		return pd.getById(publicationId);
	}
	
	public static int add(Publication publication){
		PublicationData pd = new PublicationData();
		return pd.add(publication);
	}
	
	public static boolean update(Publication publication){
		PublicationData pd = new PublicationData();
		return pd.update(publication);
	}
	
	public static boolean arePublicationsLoaded() {
		PublicationData pd = new PublicationData();
		return !pd.getAll().isEmpty();
	}
}
