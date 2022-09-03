package logic;

import java.io.IOException;
import java.util.LinkedList;

import data.PublicationData;
import data.ServiceData;
import data.UserData;
import dtos.PublicationsIndexDto;
import entities.Publication;
import entities.Service;

public class PublicationBarber {
	
	public static LinkedList<Publication> getAll() throws IOException{
		PublicationData pd = new PublicationData();
		return pd.getAll();
	}
	
	public static LinkedList<PublicationsIndexDto> getAllWithBarber() throws IOException{
		LinkedList<PublicationsIndexDto> dtoList = new LinkedList<PublicationsIndexDto>();
		PublicationData pd = new PublicationData();
		UserData ud = new UserData();
		
		LinkedList<Publication> publications = pd.getAll();
		
		for(Publication pub : publications) {
			PublicationsIndexDto pubDto = new PublicationsIndexDto(pub, ud.getById(pub.getBarberId()));
			pubDto.setBase64Image(pub.getImage());
			dtoList.add(pubDto);
			
		}
		return dtoList;
	}
	
	
	public static Publication getById(int publicationId) throws IOException{
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
	
	public static boolean arePublicationsLoaded() throws IOException{
		PublicationData pd = new PublicationData();
		return !pd.getAll().isEmpty();
	}
	
	public static boolean delete(int id) throws IOException{
	    PublicationData pd = new PublicationData();
	    Publication publication = pd.getById(id);
	    return pd.delete(publication);
	  }
}
